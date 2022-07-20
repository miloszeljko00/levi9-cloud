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
 * CostsPerServiceDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-07T15:28:06.610622400+01:00[Europe/Belgrade]")
public class CostsPerServiceDto   {
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

  @JsonProperty("cost")
  private BigDecimal cost;

  public CostsPerServiceDto serviceType(ServiceTypeEnum serviceType) {
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

  public CostsPerServiceDto cost(BigDecimal cost) {
    this.cost = cost;
    return this;
  }

  /**
   * Get cost
   * @return cost
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CostsPerServiceDto costsPerServiceDto = (CostsPerServiceDto) o;
    return Objects.equals(this.serviceType, costsPerServiceDto.serviceType) &&
        Objects.equals(this.cost, costsPerServiceDto.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceType, cost);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CostsPerServiceDto {\n");
    
    sb.append("    serviceType: ").append(toIndentedString(serviceType)).append("\n");
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
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

