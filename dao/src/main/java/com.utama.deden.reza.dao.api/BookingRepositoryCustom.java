package com.utama.deden.reza.dao.api;

import com.utama.deden.reza.entity.dto.BookingDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface BookingRepositoryCustom {
  Flux<BookingDto> findBookingPaginatedCustom(String storeId, Pageable pageable, int isDeleted);

}
