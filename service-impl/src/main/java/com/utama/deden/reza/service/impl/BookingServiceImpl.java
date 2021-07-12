package com.utama.deden.reza.service.impl;

import com.utama.deden.reza.dao.api.BookingRepository;
import com.utama.deden.reza.dao.api.BookingRepositoryCustom;
import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.constant.CommonConstant;
import com.utama.deden.reza.entity.dao.Booking;
import com.utama.deden.reza.entity.dto.BookingDto;
import com.utama.deden.reza.entity.dto.HouseDto;
import com.utama.deden.reza.libraries.utility.BeanMapperHelper;
import com.utama.deden.reza.service.api.BookingService;
import com.utama.deden.reza.service.api.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

  private final BookingRepository bookingRepository;

  private final BookingRepositoryCustom bookingRepositoryCustom;

  private final HouseService houseService;

  public BookingServiceImpl(BookingRepository bookingRepository,
      BookingRepositoryCustom bookingRepositoryCustom,
      HouseService houseService) {
    this.bookingRepository = bookingRepository;
    this.bookingRepositoryCustom = bookingRepositoryCustom;
    this.houseService = houseService;
  }

  @Override
  public Mono<Page<BookingDto>> findAll(MandatoryRequest mandatoryRequest, Pageable pageable) {
    return bookingRepositoryCustom.findBookingPaginatedCustom(mandatoryRequest.getStoreId(), pageable, CommonConstant.NOT_DELETED)
        .collectList()
        .map(bookingDtoList -> new PageImpl<>(bookingDtoList, pageable, bookingDtoList.size()));
  }

  @Override
  public Mono<BookingDto> createBooking(MandatoryRequest mandatoryRequest, BookingDto bookingDto) {
    return this.houseService.findById(mandatoryRequest, bookingDto.getHouseId())
        .flatMap(houseDto -> bookingRepository.save(this.toBooking(mandatoryRequest, bookingDto))
            .map(booking -> this.toBookingDto(booking, houseDto)))
        .doOnError(throwable -> log.info("[{}] failed on create booking {}", mandatoryRequest, throwable));
  }

  private Booking toBooking(MandatoryRequest mandatoryRequest, BookingDto bookingDto){
    return Booking
        .builder()
        .storeId(mandatoryRequest.getStoreId())
        .customerName(bookingDto.getCustomerName())
        .customerAddress(bookingDto.getCustomerAddress())
        .finalPrice(bookingDto.getFinalPrice())
        .houseId(bookingDto.getHouseId())
        .build();
  }
  private BookingDto toBookingDto(Booking booking, HouseDto houseDto){
    BookingDto bookingDto = BeanMapperHelper.map(booking, BookingDto.class);
    bookingDto.setHouse(houseDto);
    return bookingDto;
  }
}
