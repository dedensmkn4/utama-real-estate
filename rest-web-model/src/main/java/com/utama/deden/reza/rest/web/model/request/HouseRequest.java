package com.utama.deden.reza.rest.web.model.request;

import com.utama.deden.reza.entity.constant.enums.EOfferType;
import com.utama.deden.reza.entity.constant.enums.EStatus;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HouseRequest implements Serializable {
  private EOfferType offerType;

  private double offerCost;

  private double areaSquareFoot;

  private String address;

  private String street;

  private String city;

  private EStatus status;

  private BrokerRequest broker;
}
