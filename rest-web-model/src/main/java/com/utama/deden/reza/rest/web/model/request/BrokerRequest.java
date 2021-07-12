package com.utama.deden.reza.rest.web.model.request;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BrokerRequest implements Serializable {

  @NonNull
  private String brokerCode;

  @NonNull
  private String brokerName;
}
