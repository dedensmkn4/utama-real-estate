package com.utama.deden.reza.rest.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utama.deden.reza.entity.constant.enums.EOfferType;
import com.utama.deden.reza.entity.constant.enums.EStatus;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseResponse implements Serializable {

  private Long id;

  private EOfferType offerType;

  private double offerCost;

  private double areaSquareFoot;

  private String address;

  private String street;

  private String city;

  private EStatus status;

  private BrokerResponse broker;

}
