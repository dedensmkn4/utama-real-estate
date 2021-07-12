package com.utama.deden.reza.entity.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandatoryRequest implements Serializable {
    private String requestId;
    private String channelId;
    private String username;
    private String storeId;
    private String serviceId;

}
