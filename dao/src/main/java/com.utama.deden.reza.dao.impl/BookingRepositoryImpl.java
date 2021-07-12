package com.utama.deden.reza.dao.impl;

import com.utama.deden.reza.dao.api.BookingRepositoryCustom;
import com.utama.deden.reza.dao.mapper.BookingRepositoryMapper;
import com.utama.deden.reza.entity.dto.BookingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class BookingRepositoryImpl implements BookingRepositoryCustom {

  private final DatabaseClient databaseClient;

  public BookingRepositoryImpl(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @Override
  public Flux<BookingDto> findBookingPaginatedCustom(String storeId, Pageable pageable,
      int isDeleted) {
    BookingRepositoryMapper bookingRepositoryMapper = new BookingRepositoryMapper();
    return this.databaseClient
        .sql("SELECT booking.*, house.*, broker.broker_code, broker.broker_name FROM booking LEFT JOIN house ON booking.house_id=house.id LEFT JOIN broker ON house.broker_id = broker.id WHERE booking.store_id=:store_id AND booking.is_deleted=:is_deleted LIMIT :limit OFFSET :offset ")
        .bind("store_id", storeId)
        .bind("is_deleted", isDeleted)
        .bind("limit", pageable.getPageSize())
        .bind("offset", pageable.getPageNumber())
        .map(bookingRepositoryMapper::apply)
        .all();
  }

}
