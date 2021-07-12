package com.utama.deden.reza.service.api;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.dto.BookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface BookingService {

  Mono<Page<BookingDto>> findAll(MandatoryRequest mandatoryRequest, Pageable pageable);

  Mono<BookingDto> createBooking(MandatoryRequest mandatoryRequest, BookingDto bookingDto);
}
