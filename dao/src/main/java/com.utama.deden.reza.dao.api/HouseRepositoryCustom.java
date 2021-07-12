package com.utama.deden.reza.dao.api;

import com.utama.deden.reza.entity.dto.HouseDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HouseRepositoryCustom {

  Mono<HouseDto> findHouseCustom(String storeId, Long id, int isDeleted);

  Flux<HouseDto> findHousePaginatedCustom(String storeId, Pageable pageable, int isDeleted);
}
