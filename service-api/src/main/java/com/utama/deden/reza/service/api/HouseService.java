package com.utama.deden.reza.service.api;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.dto.HouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface HouseService {

  Mono<Page<HouseDto>> findAll(MandatoryRequest mandatoryRequest, Pageable page);

  Mono<HouseDto> findById(MandatoryRequest mandatoryRequest, Long id);

  Mono<HouseDto> create(MandatoryRequest mandatoryRequest, HouseDto houseDto);

  Mono<HouseDto> update(MandatoryRequest mandatoryRequest, HouseDto houseDto, Long id);

  Mono<HouseDto> delete(MandatoryRequest mandatoryRequest, Long id);
}
