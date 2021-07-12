package com.utama.deden.reza.dao.mapper;

import com.utama.deden.reza.dao.util.HouseHelper;
import com.utama.deden.reza.entity.constant.fields.BaseSqlFields;
import com.utama.deden.reza.entity.constant.fields.BookingFields;
import com.utama.deden.reza.entity.constant.fields.HouseFields;
import com.utama.deden.reza.entity.dto.BookingDto;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.Optional;
import java.util.function.BiFunction;

public class BookingRepositoryMapper  implements BiFunction<Row, RowMetadata, BookingDto> {

  @Override
  public BookingDto apply(Row row, RowMetadata rowMetadata) {
    return BookingDto.builder()
        .id(row.get(BaseSqlFields.ID, Long.class))
        .houseId(row.get(BookingFields.HOUSE_ID, Long.class))
        .customerAddress(row.get(BookingFields.CUSTOMER_ADDRESS, String.class))
        .customerName(row.get(BookingFields.CUSTOMER_NAME, String.class))
        .finalPrice(this.ifEmptyDefault(row.get(BookingFields.FINAL_PRICE, Double.class)))
        .house(HouseHelper
            .toHouseDtoRaw(row, rowMetadata, row.get(BookingFields.HOUSE_ID, Long.class), row.get(HouseFields.BROKER_ID, Long.class)))
        .build();
  }

  private double ifEmptyDefault(Double area){
    return Optional.ofNullable(area)
        .orElse(0D);
  }
}
