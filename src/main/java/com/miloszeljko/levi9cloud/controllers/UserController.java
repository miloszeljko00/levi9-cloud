package com.miloszeljko.levi9cloud.controllers;

import com.miloszeljko.levi9cloud.api.UserApi;
import com.miloszeljko.levi9cloud.model.CostsDto;
import com.miloszeljko.levi9cloud.model.CostsPerServiceDto;
import com.miloszeljko.levi9cloud.models.Payload;
import com.miloszeljko.levi9cloud.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.miloszeljko.levi9cloud.models.User.users;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<CostsDto> cloudPricingControllerGetCosts(BigDecimal userId, List<String> serviceTypes, BigDecimal untilDate) {

        // Pricing constants from specification
        final int INVOCATION_FREE_TIER = 10;
        final int TIME_FREE_TIER = 36000;
        final int DATA_FREE_TIER = 1024;
        final int INVOCATION_STEP = 10;
        final double PRICE_PER_STEP = 0.01;

        // User requested by GET request with userId
        User requestedUser = null;
        boolean userFound = false;

        for(User user : users){
            if(user.getUserId().equals(userId)){
                userFound = true;
                requestedUser = user;
            }
        }
        if(!userFound){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Cost of all services combined
        BigDecimal totalCost = BigDecimal.valueOf(0);

        // Cost of serverless function service
        BigDecimal funcCost = BigDecimal.valueOf(0);

        // Cost of Database service
        BigDecimal dbCost = BigDecimal.valueOf(0);

        // Cost of Object storage service
        BigDecimal osCost = BigDecimal.valueOf(0);

        // Cost of virtual machine service
        BigDecimal vmCost = BigDecimal.valueOf(0);

        // Cost of network service
        BigDecimal networkCost = BigDecimal.valueOf(0);

        // Number of times serverless service invoked
        BigDecimal funcInvocations = BigDecimal.valueOf(0);

        // Number of times database service invoked
        BigDecimal dbInvocations = BigDecimal.valueOf(0);

        // Total running time of virtual machine prior to untilDate
        BigDecimal vmTime = BigDecimal.valueOf(0);

        // Total data stored with database service
        BigDecimal dbData = BigDecimal.valueOf(0);

        // Total data stored with object storage service
        BigDecimal osData = BigDecimal.valueOf(0);

        // Total data transferred over metered network
        BigDecimal networkData = BigDecimal.valueOf(0);

        // List of all payloads sent by requestedUser
        List<Payload> payloads =  requestedUser.getPayloads();


        boolean vmRunning = false;

        // Last timestamp when virtual machine service was started
        BigDecimal vmStartTime = BigDecimal.valueOf(0);


        for(Payload payload : payloads) {
            if (payload.getTimestamp().compareTo(untilDate) < 0) {
                switch (payload.getServiceType()) {
                    case FUNC:
                        switch (payload.getActionType()) {

                            // Function invocations and network data counted for payment
                            case EXEC:
                                funcInvocations = funcInvocations.add(BigDecimal.valueOf(1));
                                networkData = networkData.add(payload.getPayloadSizeMb());
                                break;
                            default:
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                        break;
                    case DB:
                        switch (payload.getActionType()) {

                            // Database invocations and total data stored counted for payment
                            case INSERT:
                                dbInvocations = dbInvocations.add(BigDecimal.valueOf(1));
                                dbData = dbData.add(payload.getPayloadSizeMb());
                                break;

                            // Database invocations counted for payment
                            case SELECT:
                            case SOFT_DELETE:
                                dbInvocations = dbInvocations.add(BigDecimal.valueOf(1));
                                break;
                            default:
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                        break;
                    case OBJECT_STORAGE:
                        switch (payload.getActionType()) {

                            // Data stored in object storage counted for payment
                            case PUT:
                                osData = osData.add(payload.getPayloadSizeMb());
                                break;

                            // Data transferred over network counted for payment
                            case GET:
                                networkData = networkData.add(payload.getPayloadSizeMb());
                                break;

                            // Nothing counted for payment
                            case SOFT_DELETE:
                                break;
                            default:
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                        break;
                    case VM:
                        switch (payload.getActionType()) {

                            // Checking if virtual machine is running, if not,
                            // starting it and saving current timestamp as new vmStartTime
                            case START:
                                if (vmRunning){
                                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                                }
                                vmRunning = true;
                                vmStartTime = payload.getTimestamp();
                                break;

                            // Checking if virtual machine is running, if it is,
                            // stopping it and adding difference between current timestamp and starting timestamp
                            case STOP:
                                if (!vmRunning){
                                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                                }
                                vmRunning = false;
                                vmTime = vmTime.add(payload.getTimestamp());
                                vmTime = vmTime.subtract(vmStartTime);
                                break;
                            default:
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                        break;
                    default:
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }

        // If virtual machine is still running at time of calculation, use untilDate as stopped timestamp
        if(vmRunning){
            vmTime = vmTime.add(untilDate);
            vmTime = vmTime.subtract(vmStartTime);
        }


        // Calculating serverless function service cost
        funcInvocations = funcInvocations.subtract(BigDecimal.valueOf(INVOCATION_FREE_TIER));
        if(funcInvocations.intValue() > 0){
            BigDecimal[] invocation = funcInvocations.divideAndRemainder(BigDecimal.valueOf(INVOCATION_STEP));
            if(invocation[1].doubleValue() != 0){
                invocation[0] = invocation[0].add(BigDecimal.valueOf(1));
            }
            funcCost = funcCost.add(invocation[0].multiply(BigDecimal.valueOf(PRICE_PER_STEP)));
        }


        // Calculating database service cost
        dbInvocations = dbInvocations.subtract(BigDecimal.valueOf(INVOCATION_FREE_TIER));
        if(dbInvocations.intValue() > 0){
            BigDecimal[] invocation = dbInvocations.divideAndRemainder(BigDecimal.valueOf(INVOCATION_STEP));
            if(invocation[1].doubleValue() != 0){
                invocation[0] = invocation[0].add(BigDecimal.valueOf(1));
            }
            dbCost = dbCost.add(invocation[0].multiply(BigDecimal.valueOf(PRICE_PER_STEP)));
        }
        dbData = dbData.subtract(BigDecimal.valueOf(DATA_FREE_TIER));
        if(dbData.intValue() > 0){
            dbCost = dbCost.add(dbData.multiply(BigDecimal.valueOf(PRICE_PER_STEP)));
        }


        // Calculating object storage service cost
        osData = osData.subtract(BigDecimal.valueOf(DATA_FREE_TIER));
        if(osData.intValue() > 0){
            osCost = osCost.add(osData.multiply(BigDecimal.valueOf(PRICE_PER_STEP)));
        }

        // Calculating virtual machine service cost
        vmTime = vmTime.subtract(BigDecimal.valueOf(TIME_FREE_TIER));
        vmTime = vmTime.multiply(BigDecimal.valueOf(PRICE_PER_STEP));
        if(vmTime.doubleValue() < 0){
            vmTime = BigDecimal.valueOf(0);
        }
        vmCost = vmTime;

        // Calculating network service cost
        networkData = networkData.subtract(BigDecimal.valueOf(DATA_FREE_TIER));
        if(networkData.intValue() > 0){
            networkCost = networkCost.add(networkData.multiply(BigDecimal.valueOf(PRICE_PER_STEP)));
        }

        // Calculating total cost
        totalCost = funcCost.add(dbCost.add(osCost.add(vmCost.add(networkCost))));

        // Output object, sent as response to request
        CostsDto userCosts = new CostsDto();

        userCosts.setUserId(userId);
        userCosts.setTotalCosts(totalCost);

        if(serviceTypes != null){

            // Adding selected services to response object
            for(String serviceType :serviceTypes){
                CostsPerServiceDto serviceCost = new CostsPerServiceDto();
                switch (serviceType){
                    case "FUNC":
                        serviceCost.setServiceType(CostsPerServiceDto.ServiceTypeEnum.FUNC);
                        serviceCost.setCost(funcCost);
                        break;
                    case "DB":
                        serviceCost.setServiceType(CostsPerServiceDto.ServiceTypeEnum.DB);
                        serviceCost.setCost(dbCost);
                        break;
                    case "OBJECT_STORAGE":
                        serviceCost.setServiceType(CostsPerServiceDto.ServiceTypeEnum.OBJECT_STORAGE);
                        serviceCost.setCost(osCost);
                        break;
                    case "VM":
                        serviceCost.setServiceType(CostsPerServiceDto.ServiceTypeEnum.VM);
                        serviceCost.setCost(vmCost);
                        break;
                    case "NETWORK":
                        serviceCost.setServiceType(CostsPerServiceDto.ServiceTypeEnum.NETWORK);
                        serviceCost.setCost(networkCost);
                        break;
                    default:
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                userCosts.addCostsPerServiceItem(serviceCost);
            }
        }
        return new ResponseEntity<>(userCosts, HttpStatus.OK);
    }
}
