package com.utama.deden.reza.rest.web.controller;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.constant.ApiPath;
import com.utama.deden.reza.entity.constant.enums.EResponseCode;
import com.utama.deden.reza.entity.dto.BrokerDto;
import com.utama.deden.reza.entity.dto.HouseDto;
import com.utama.deden.reza.libraries.utility.BaseResponseHelper;
import com.utama.deden.reza.libraries.utility.BeanMapperHelper;
import com.utama.deden.reza.rest.web.model.request.HouseRequest;
import com.utama.deden.reza.rest.web.model.response.BaseResponse;
import com.utama.deden.reza.rest.web.model.response.HouseResponse;
import com.utama.deden.reza.service.api.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(ApiPath.PROPERTY_ROUTE)
@Api("House Properties Api's")
@Slf4j
public class HouseController {

  private final HouseService houseService;

  public HouseController(HouseService houseService) {
    this.houseService = houseService;
  }

  @ApiOperation(value = "find all broker from database")
  @GetMapping(ApiPath.ALL)
  public Mono<BaseResponse<Page<HouseResponse>>> findAll(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestParam Integer page, @RequestParam Integer pageSize){
    log.info("[{}] findAll house", mandatoryRequest);
    return houseService.findAll(mandatoryRequest, PageRequest.of(page, pageSize))
        .map(this::toHouseResponse)
        .map(houseResponsePage -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, houseResponsePage))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "find property by id")
  @GetMapping(value = ApiPath.ID)
  public Mono<BaseResponse<HouseResponse>> getHouse(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @PathVariable Long id){
    log.info("[{}] getHouse id = {}", mandatoryRequest, id);
    return houseService.findById(mandatoryRequest, id)
        .map(propertyDto -> BeanMapperHelper.map(propertyDto, HouseResponse.class))
        .map(houseResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, houseResponse))
        .subscribeOn(Schedulers.single());
  }


  @ApiOperation(value = "create house to database")
  @PostMapping
  public Mono<BaseResponse<HouseResponse>> create(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestBody HouseRequest houseRequest){
    log.info("[{}] create house = {}", mandatoryRequest, houseRequest);
    return houseService.create(mandatoryRequest, this.toHouseDto(houseRequest))
        .map(houseDto -> BeanMapperHelper.map(houseDto, HouseResponse.class))
        .map(houseResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, houseResponse))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "create house to database")
  @PutMapping(ApiPath.ID)
  public Mono<BaseResponse<HouseResponse>> update(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestBody HouseRequest houseRequest, @PathVariable Long id){
    log.info("[{}] update house = {} id = {}", mandatoryRequest, houseRequest, id);
    return houseService.update(mandatoryRequest, this.toHouseDto(houseRequest), id)
        .map(houseDto -> BeanMapperHelper.map(houseDto, HouseResponse.class))
        .map(houseResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, houseResponse))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "find property by id")
  @DeleteMapping(value = ApiPath.ID)
  public Mono<BaseResponse<HouseResponse>> delete(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @PathVariable Long id){
    log.info("[{}] delete house id = {}", mandatoryRequest, id);
    return houseService.delete(mandatoryRequest, id)
        .map(propertyDto -> BeanMapperHelper.map(propertyDto, HouseResponse.class))
        .map(houseResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, houseResponse))
        .subscribeOn(Schedulers.single());
  }

  private Page<HouseResponse> toHouseResponse(Page<HouseDto> houseDtoPage){
    return houseDtoPage.map(houseDto -> BeanMapperHelper.map(houseDto, HouseResponse.class));
  }

  private HouseDto toHouseDto(HouseRequest houseRequest){
    return HouseDto.builder()
        .address(houseRequest.getAddress())
        .areaSquareFoot(houseRequest.getAreaSquareFoot())
        .city(houseRequest.getCity())
        .offerCost(houseRequest.getOfferCost())
        .offerType(houseRequest.getOfferType())
        .street(houseRequest.getStreet())
        .status(houseRequest.getStatus())
        .broker(BrokerDto.builder()
            .brokerCode(houseRequest.getBroker().getBrokerCode())
            .brokerName(houseRequest.getBroker().getBrokerName())
            .build())
        .build();
  }

}
