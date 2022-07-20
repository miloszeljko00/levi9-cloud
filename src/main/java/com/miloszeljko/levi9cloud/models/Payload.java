package com.miloszeljko.levi9cloud.models;

import com.miloszeljko.levi9cloud.model.ActionDto;

import java.math.BigDecimal;

public class Payload {
    private BigDecimal userId;
    private ActionDto.ServiceTypeEnum serviceType;
    private ActionDto.ActionTypeEnum actionType;
    private BigDecimal timestamp;
    private BigDecimal payloadSizeMb;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public ActionDto.ServiceTypeEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(ActionDto.ServiceTypeEnum serviceType) {
        this.serviceType = serviceType;
    }

    public ActionDto.ActionTypeEnum getActionType() {
        return actionType;
    }

    public void setActionType(ActionDto.ActionTypeEnum actionType) {
        this.actionType = actionType;
    }

    public BigDecimal getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigDecimal timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getPayloadSizeMb() {
        return payloadSizeMb;
    }

    public void setPayloadSizeMb(BigDecimal payloadSizeMb) {
        this.payloadSizeMb = payloadSizeMb;
    }

    public Payload(BigDecimal userId, ActionDto.ServiceTypeEnum serviceType, ActionDto.ActionTypeEnum actionType, BigDecimal timestamp, BigDecimal payloadSizeMb) {
        this.userId = userId;
        this.serviceType = serviceType;
        this.actionType = actionType;
        this.timestamp = timestamp;
        this.payloadSizeMb = payloadSizeMb;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "userId=" + userId +
                ", serviceType=" + serviceType +
                ", actionType=" + actionType +
                ", timestamp=" + timestamp +
                ", payloadSizeMb=" + payloadSizeMb +
                '}';
    }
}
