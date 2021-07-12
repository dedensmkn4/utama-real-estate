package com.utama.deden.reza.rest.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse implements Serializable {

  private Long id;

  private String customerName;

  private String customerAddress;

  private double finalPrice;

  private HouseResponse house;

}
