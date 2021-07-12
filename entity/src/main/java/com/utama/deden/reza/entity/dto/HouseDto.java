package com.utama.deden.reza.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utama.deden.reza.entity.constant.enums.EOfferType;
import com.utama.deden.reza.entity.constant.enums.EStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class HouseDto implements Serializable {

  private Long id;

  private EOfferType offerType;

  private double offerCost;

  private double areaSquareFoot;

  private String address;

  private String street;

  private String city;

  private EStatus status;

  private BrokerDto broker;

}
