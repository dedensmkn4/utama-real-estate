package com.utama.deden.reza.dao.impl;

import com.utama.deden.reza.dao.api.HouseRepositoryCustom;
import com.utama.deden.reza.dao.mapper.HouseRepositoryMapper;
import com.utama.deden.reza.entity.dto.HouseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class HouseRepositoryCustomImpl implements HouseRepositoryCustom {

  private final DatabaseClient databaseClient;

  public HouseRepositoryCustomImpl(DatabaseClient databaseClient) {
    this.databaseClient = databaseClient;
  }

  @Override
  public Mono<HouseDto> findHouseCustom(String storeId, Long id, int isDeleted) {
    HouseRepositoryMapper propertyRepositoryMapper = new HouseRepositoryMapper();
    return this.databaseClient
        .sql("SELECT house.*, broker.broker_code, broker.broker_name FROM house LEFT JOIN broker ON house.broker_id=broker.id WHERE house.id=:id AND house.store_id=:store_id AND house.is_deleted=:is_deleted")
        .bind("id", id)
        .bind("store_id", storeId)
        .bind("is_deleted", isDeleted)
        .map(propertyRepositoryMapper::apply)
        .first();

  }

  @Override
  public Flux<HouseDto> findHousePaginatedCustom(String storeId, Pageable pageable, int isDeleted) {
    HouseRepositoryMapper propertyRepositoryMapper = new HouseRepositoryMapper();
    return this.databaseClient
        .sql("SELECT house.*, broker.broker_code, broker.broker_name FROM house LEFT JOIN broker ON house.broker_id=broker.id WHERE house.store_id=:store_id AND house.is_deleted=:is_deleted LIMIT :limit OFFSET :offset ")
        .bind("store_id", storeId)
        .bind("is_deleted", isDeleted)
        .bind("limit", pageable.getPageSize())
        .bind("offset", pageable.getPageNumber())
        .map(propertyRepositoryMapper::apply)
        .all();
  }

}
