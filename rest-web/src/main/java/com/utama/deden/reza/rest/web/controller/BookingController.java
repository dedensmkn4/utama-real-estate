package com.utama.deden.reza.rest.web.controller;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.constant.ApiPath;
import com.utama.deden.reza.entity.constant.enums.EResponseCode;
import com.utama.deden.reza.entity.dto.BookingDto;
import com.utama.deden.reza.libraries.utility.BaseResponseHelper;
import com.utama.deden.reza.libraries.utility.BeanMapperHelper;
import com.utama.deden.reza.rest.web.model.request.BookingRequest;
import com.utama.deden.reza.rest.web.model.response.BaseResponse;
import com.utama.deden.reza.rest.web.model.response.BookingResponse;
import com.utama.deden.reza.service.api.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = ApiPath.BOOKING_ROUTE)
@Api("Booking Properties Api's")
@Slf4j
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @ApiOperation(value = "find all booking from database")
  @GetMapping(ApiPath.ALL)
  public Mono<BaseResponse<Page<BookingResponse>>> findAll(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestParam Integer page, @RequestParam Integer pageSize){
    log.info("[{}] findAll booking", mandatoryRequest);
    return bookingService.findAll(mandatoryRequest, PageRequest.of(page, pageSize))
        .map(this::toPageBookingResponse)
        .map(bookingResponsePage -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, bookingResponsePage))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "create booking to database")
  @PostMapping
  public Mono<BaseResponse<BookingResponse>> create(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestBody BookingRequest bookingRequest){
    log.info("[{}] create booking = {}", mandatoryRequest, bookingRequest);
    return bookingService.createBooking(mandatoryRequest, this.toBookingDto(bookingRequest))
        .map(bookingDto -> BeanMapperHelper.map(bookingDto, BookingResponse.class))
        .map(bookingResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, bookingResponse))
        .subscribeOn(Schedulers.single());
  }

  private Page<BookingResponse> toPageBookingResponse(Page<BookingDto> bookingDtoPage){
    return bookingDtoPage.map(bookingDto -> BeanMapperHelper.map(bookingDto, BookingResponse.class));
  }

  private BookingDto toBookingDto(BookingRequest bookingRequest){
    return BookingDto.builder()
        .customerName(bookingRequest.getCustomerName())
        .customerAddress(bookingRequest.getCustomerAddress())
        .finalPrice(bookingRequest.getFinalPrice())
        .houseId(bookingRequest.getHouseId())
        .build();
  }

}
