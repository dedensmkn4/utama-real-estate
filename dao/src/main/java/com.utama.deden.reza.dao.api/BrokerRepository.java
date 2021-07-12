package com.utama.deden.reza.dao.api;

import com.utama.deden.reza.entity.dao.Broker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrokerRepository  extends ReactiveCrudRepository<Broker, Long> {

  Flux<Broker> findAllByStoreIdAndIsDeleted(String storeId, int isDeleted);

  Mono<Broker> findByIdAndStoreIdAndIsDeleted(Long id, String storeId, int isDeleted);

  Mono<Broker> findByBrokerCodeAndStoreIdAndIsDeleted(String code, String storeId, int isDeleted);

  Mono<Broker> findByBrokerCodeAndStoreIdAndIsDeletedAndIdNot(String code, String storedId, int isDeleted, Long id);
}
