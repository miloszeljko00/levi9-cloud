package com.miloszeljko.levi9cloud.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ActionDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-07T15:28:06.610622400+01:00[Europe/Belgrade]")
public class ActionDto   {
  @JsonProperty("userId")
  private BigDecimal userId;

  @JsonProperty("timestamp")
  private BigDecimal timestamp;

  /**
   * Gets or Sets actionType
   */
  public enum ActionTypeEnum {
    EXEC("EXEC"),
    
    INSERT("INSERT"),
    
    SELECT("SELECT"),
    
    SOFT_DELETE("SOFT_DELETE"),
    
    PUT("PUT"),
    
    GET("GET"),
    
    START("START"),
    
    STOP("STOP");

    private String value;

    ActionTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ActionTypeEnum fromValue(String value) {
      for (ActionTypeEnum b : ActionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("actionType")
  private ActionTypeEnum actionType;

  /**
   * Gets or Sets serviceType
   */
  public enum ServiceTypeEnum {
    FUNC("FUNC"),
    
    DB("DB"),
    
    OBJECT_STORAGE("OBJECT_STORAGE"),
    
    VM("VM"),
    
    NETWORK("NETWORK");

    private String value;

    ServiceTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ServiceTypeEnum fromValue(String value) {
      for (ServiceTypeEnum b : ServiceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("serviceType")
  private ServiceTypeEnum serviceType;

  @JsonProperty("payloadSizeMb")
  private BigDecimal payloadSizeMb;

  public ActionDto userId(BigDecimal userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getUserId() {
    return userId;
  }

  public void setUserId(BigDecimal userId) {
    this.userId = userId;
  }

  public ActionDto timestamp(BigDecimal timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(BigDecimal timestamp) {
    this.timestamp = timestamp;
  }

  public ActionDto actionType(ActionTypeEnum actionType) {
    this.actionType = actionType;
    return this;
  }

  /**
   * Get actionType
   * @return actionType
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public ActionTypeEnum getActionType() {
    return actionType;
  }

  public void setActionType(ActionTypeEnum actionType) {
    this.actionType = actionType;
  }

  public ActionDto serviceType(ServiceTypeEnum serviceType) {
    this.serviceType = serviceType;
    return this;
  }

  /**
   * Get serviceType
   * @return serviceType
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public ServiceTypeEnum getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceTypeEnum serviceType) {
    this.serviceType = serviceType;
  }

  public ActionDto payloadSizeMb(BigDecimal payloadSizeMb) {
    this.payloadSizeMb = payloadSizeMb;
    return this;
  }

  /**
   * Get payloadSizeMb
   * @return payloadSizeMb
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getPayloadSizeMb() {
    return payloadSizeMb;
  }

  public void setPayloadSizeMb(BigDecimal payloadSizeMb) {
    this.payloadSizeMb = payloadSizeMb;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActionDto actionDto = (ActionDto) o;
    return Objects.equals(this.userId, actionDto.userId) &&
        Objects.equals(this.timestamp, actionDto.timestamp) &&
        Objects.equals(this.actionType, actionDto.actionType) &&
        Objects.equals(this.serviceType, actionDto.serviceType) &&
        Objects.equals(this.payloadSizeMb, actionDto.payloadSizeMb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, timestamp, actionType, serviceType, payloadSizeMb);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionDto {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    actionType: ").append(toIndentedString(actionType)).append("\n");
    sb.append("    serviceType: ").append(toIndentedString(serviceType)).append("\n");
    sb.append("    payloadSizeMb: ").append(toIndentedString(payloadSizeMb)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

