package com.utama.deden.reza.dao.mapper;

import com.utama.deden.reza.dao.util.HouseHelper;
import com.utama.deden.reza.entity.constant.fields.BaseSqlFields;
import com.utama.deden.reza.entity.constant.fields.HouseFields;
import com.utama.deden.reza.entity.dto.HouseDto;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.function.BiFunction;


public class HouseRepositoryMapper implements BiFunction<Row, RowMetadata, HouseDto> {

  @Override
  public HouseDto apply(Row row, RowMetadata rowMetadata) {
    return HouseHelper.toHouseDtoRaw(row, rowMetadata, row.get(BaseSqlFields.ID, Long.class), row.get(HouseFields.BROKER_ID, Long.class));
  }

}
