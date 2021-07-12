package com.utama.deden.reza.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class BookingDto implements Serializable {

  private Long id;

  private String customerName;

  private String customerAddress;

  private double finalPrice;

  private Long houseId;

  private HouseDto house;

}
