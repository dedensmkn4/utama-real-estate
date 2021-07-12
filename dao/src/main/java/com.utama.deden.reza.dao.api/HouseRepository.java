package com.utama.deden.reza.dao.api;

import com.utama.deden.reza.entity.dao.House;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface HouseRepository extends ReactiveCrudRepository<House, Long>{

  Mono<House> findByIdAndStoreIdAndIsDeleted(Long id, String storeId, int isDeleted);

}
