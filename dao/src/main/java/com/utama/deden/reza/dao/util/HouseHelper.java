package com.utama.deden.reza.dao.util;

import com.utama.deden.reza.entity.constant.enums.EOfferType;
import com.utama.deden.reza.entity.constant.enums.EStatus;
import com.utama.deden.reza.entity.constant.fields.BrokerFields;
import com.utama.deden.reza.entity.constant.fields.HouseFields;
import com.utama.deden.reza.entity.dto.BrokerDto;
import com.utama.deden.reza.entity.dto.HouseDto;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.Optional;

public class HouseHelper {

  public static HouseDto toHouseDtoRaw(Row row, RowMetadata rowMetadata, Long idHouse, Long idBroker){
    return HouseDto.builder()
        .id(idHouse)
        .address(row.get(HouseFields.ADDRESS, String.class))
        .status(EStatus.valueOf(row.get(HouseFields.STATUS, String.class)))
        .street(row.get(HouseFields.STREET, String.class))
        .city(row.get(HouseFields.CITY, String.class))
        .areaSquareFoot(HouseHelper.ifEmptyDefault(row.get(HouseFields.AREA_SQUARE_FOOT, Double.class)))
        .offerCost(HouseHelper.ifEmptyDefault(row.get(HouseFields.AREA_SQUARE_FOOT, Double.class)))
        .offerType(EOfferType.valueOf(row.get(HouseFields.OFFER_TYPE, String.class)))
        .broker(
            BrokerDto.builder()
                .id(idBroker)
                .brokerCode(row.get(BrokerFields.BROKER_CODE, String.class))
                .brokerName(row.get(BrokerFields.BROKER_NAME, String.class))
                .build())
        .build();
  }

  private static double ifEmptyDefault(Double area){
    return Optional.ofNullable(area)
        .orElse(0D);
  }

  private HouseHelper(){}
}
