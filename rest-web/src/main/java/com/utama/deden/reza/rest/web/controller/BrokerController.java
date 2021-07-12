package com.utama.deden.reza.rest.web.controller;

import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.constant.ApiPath;
import com.utama.deden.reza.entity.constant.enums.EResponseCode;
import com.utama.deden.reza.entity.dto.BrokerDto;
import com.utama.deden.reza.libraries.utility.BaseResponseHelper;
import com.utama.deden.reza.libraries.utility.BeanMapperHelper;
import com.utama.deden.reza.rest.web.model.request.BrokerRequest;
import com.utama.deden.reza.rest.web.model.response.BaseResponse;
import com.utama.deden.reza.rest.web.model.response.BrokerResponse;
import com.utama.deden.reza.service.api.BrokerService;
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
@RequestMapping(value = ApiPath.BROKER_ROUTE)
@Api("Broker Properties Api's")
@Slf4j
public class BrokerController {

  private final BrokerService brokerService;

  public BrokerController(BrokerService brokerService) {
    this.brokerService = brokerService;
  }

  @ApiOperation(value = "find all broker from database")
  @GetMapping(ApiPath.ALL)
  public Mono<BaseResponse<Page<BrokerResponse>>> findAll(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestParam Integer page, @RequestParam Integer pageSize){
    log.info("[{}] findAll broker", mandatoryRequest);
    return brokerService.findAll(mandatoryRequest, PageRequest.of(page, pageSize))
        .map(this::toPageBrokerResponse)
        .map(brokerResponsePage -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, brokerResponsePage))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "find broker by id")
  @GetMapping(value = ApiPath.ID)
  public Mono<BaseResponse<BrokerResponse>> getBroker(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @PathVariable Long id){
    log.info("[{}] getBroker id = {}", mandatoryRequest, id);
    return brokerService.findById(mandatoryRequest, id)
        .map(brokerDto -> BeanMapperHelper.map(brokerDto, BrokerResponse.class))
        .map(brokerResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, brokerResponse))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "create broker to database")
  @PostMapping
  public Mono<BaseResponse<BrokerResponse>> create(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestBody BrokerRequest brokerRequest){
    log.info("[{}] create broker = {}", mandatoryRequest, brokerRequest);
    return brokerService.create(mandatoryRequest, this.toBrokerDto(brokerRequest))
        .map(brokerDto -> BeanMapperHelper.map(brokerDto, BrokerResponse.class))
        .map(brokerResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, brokerResponse))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "update broker to database")
  @PutMapping(ApiPath.ID)
  public Mono<BaseResponse<BrokerResponse>> update(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @RequestBody BrokerRequest brokerRequest, @PathVariable Long id){
    log.info("[{}] update broker = {} id = {} ", mandatoryRequest, brokerRequest, id);
    return brokerService.update(mandatoryRequest, this.toBrokerDto(brokerRequest), id)
        .map(brokerDto -> BeanMapperHelper.map(brokerDto, BrokerResponse.class))
        .map(brokerResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, brokerResponse))
        .subscribeOn(Schedulers.single());
  }

  @ApiOperation(value = "delete broker to database")
  @DeleteMapping(ApiPath.ID)
  public Mono<BaseResponse<BrokerResponse>> delete(
      @Validated @ModelAttribute MandatoryRequest mandatoryRequest, @PathVariable Long id){
    log.info("[{}] delete broker  id = {} ", mandatoryRequest, id);
    return brokerService.delete(mandatoryRequest, id)
        .map(brokerDto -> BeanMapperHelper.map(brokerDto, BrokerResponse.class))
        .map(brokerResponse -> BaseResponseHelper.constructResponse(EResponseCode.SUCCESS
            .getCode(), EResponseCode.SUCCESS.getMessage(), null, brokerResponse))
        .subscribeOn(Schedulers.single());
  }

  private Page<BrokerResponse> toPageBrokerResponse(Page<BrokerDto> brokerDtoPage){
    return brokerDtoPage.map(brokerDto -> BeanMapperHelper.map(brokerDto, BrokerResponse.class));
  }

  private BrokerDto toBrokerDto(BrokerRequest brokerRequest){
    return BrokerDto.builder()
        .brokerCode(brokerRequest.getBrokerCode())
        .brokerName(brokerRequest.getBrokerName())
        .build();
  }
}
