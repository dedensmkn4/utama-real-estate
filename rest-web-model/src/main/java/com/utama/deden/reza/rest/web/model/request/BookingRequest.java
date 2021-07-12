package com.utama.deden.reza.rest.web.model.request;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BookingRequest implements Serializable {

  @NonNull
  private String customerName;

  @NonNull
  private String customerAddress;

  @NonNull
  private double finalPrice;

  @NonNull
  private Long houseId;
}
