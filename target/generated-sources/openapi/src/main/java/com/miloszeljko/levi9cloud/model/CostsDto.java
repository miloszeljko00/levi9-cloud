package com.miloszeljko.levi9cloud.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.miloszeljko.levi9cloud.model.CostsPerServiceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CostsDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-07T15:28:06.610622400+01:00[Europe/Belgrade]")
public class CostsDto   {
  @JsonProperty("userId")
  private BigDecimal userId;

  @JsonProperty("totalCosts")
  private BigDecimal totalCosts;

  @JsonProperty("costsPerService")
  @Valid
  private List<CostsPerServiceDto> costsPerService = new ArrayList<>();

  public CostsDto userId(BigDecimal userId) {
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

  public CostsDto totalCosts(BigDecimal totalCosts) {
    this.totalCosts = totalCosts;
    return this;
  }

  /**
   * Get totalCosts
   * @return totalCosts
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getTotalCosts() {
    return totalCosts;
  }

  public void setTotalCosts(BigDecimal totalCosts) {
    this.totalCosts = totalCosts;
  }

  public CostsDto costsPerService(List<CostsPerServiceDto> costsPerService) {
    this.costsPerService = costsPerService;
    return this;
  }

  public CostsDto addCostsPerServiceItem(CostsPerServiceDto costsPerServiceItem) {
    this.costsPerService.add(costsPerServiceItem);
    return this;
  }

  /**
   * Get costsPerService
   * @return costsPerService
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<CostsPerServiceDto> getCostsPerService() {
    return costsPerService;
  }

  public void setCostsPerService(List<CostsPerServiceDto> costsPerService) {
    this.costsPerService = costsPerService;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CostsDto costsDto = (CostsDto) o;
    return Objects.equals(this.userId, costsDto.userId) &&
        Objects.equals(this.totalCosts, costsDto.totalCosts) &&
        Objects.equals(this.costsPerService, costsDto.costsPerService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, totalCosts, costsPerService);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CostsDto {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    totalCosts: ").append(toIndentedString(totalCosts)).append("\n");
    sb.append("    costsPerService: ").append(toIndentedString(costsPerService)).append("\n");
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

