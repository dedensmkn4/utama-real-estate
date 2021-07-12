package com.utama.deden.reza.service.api;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.dto.BrokerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface BrokerService {

  Mono<Page<BrokerDto>> findAll(MandatoryRequest mandatoryRequest, Pageable page);

  Mono<BrokerDto> findById(MandatoryRequest mandatoryRequest, Long id);

  Mono<BrokerDto> create(MandatoryRequest mandatoryRequest, BrokerDto brokerDto);

  Mono<BrokerDto> update(MandatoryRequest mandatoryRequest, BrokerDto brokerDto, Long id);

  Mono<BrokerDto> delete(MandatoryRequest mandatoryRequest, Long id);

}
