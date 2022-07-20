package com.miloszeljko.levi9cloud.controllers;

import com.miloszeljko.levi9cloud.api.ActionsApi;
import com.miloszeljko.levi9cloud.model.ActionDto;
import com.miloszeljko.levi9cloud.models.Payload;
import com.miloszeljko.levi9cloud.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.miloszeljko.levi9cloud.models.User.findUser;


@RestController
public class ActionsController implements ActionsApi {

    // Accepts list of actions sent by POST request and sorts them according to userId
    @Override
    public ResponseEntity<Void> cloudPricingControllerStore(List<ActionDto> actionDto) {

        for(ActionDto action : actionDto) {
            BigDecimal userId = action.getUserId();
            BigDecimal timestamp = action.getTimestamp();
            ActionDto.ActionTypeEnum actionType = action.getActionType();
            ActionDto.ServiceTypeEnum serviceType = action.getServiceType();
            BigDecimal payloadSizeMb = action.getPayloadSizeMb();

            User user = findUser(userId);
            Payload payload = new Payload(userId,serviceType,actionType,timestamp,payloadSizeMb);
            user.addPayload(payload);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
