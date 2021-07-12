package com.utama.deden.reza.service.impl;

import com.utama.deden.reza.dao.api.BrokerRepository;
import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.constant.CommonConstant;
import com.utama.deden.reza.entity.constant.enums.EResponseCode;
import com.utama.deden.reza.entity.dao.Broker;
import com.utama.deden.reza.entity.dto.BrokerDto;
import com.utama.deden.reza.libraries.exception.BusinessLogicException;
import com.utama.deden.reza.libraries.utility.BeanMapperHelper;
import com.utama.deden.reza.service.api.BrokerService;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BrokerServiceImpl implements BrokerService {

  private final BrokerRepository brokerRepository;

  public BrokerServiceImpl(BrokerRepository brokerRepository) {
    this.brokerRepository = brokerRepository;
  }

  @Override
  public Mono<Page<BrokerDto>> findAll(MandatoryRequest mandatoryRequest, Pageable page) {
    return brokerRepository.findAllByStoreIdAndIsDeleted(mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED)
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        )
        .collectList()
        .map(brokers -> new PageImpl<>(
            brokers
                .stream()
                .skip((long) page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize())
                .map(broker -> BeanMapperHelper.map(broker, BrokerDto.class))
                .collect(Collectors.toList()), page, brokers.size()
        ));
  }

  @Override
  public Mono<BrokerDto> findById(MandatoryRequest mandatoryRequest, Long id) {
    return this.findExist(mandatoryRequest, id)
        .map(broker -> BeanMapperHelper.map(broker, BrokerDto.class));
  }

  @Override
  public Mono<BrokerDto> create(MandatoryRequest mandatoryRequest, BrokerDto brokerDto) {
    log.info("[{}] save broker = {} ", mandatoryRequest, brokerDto);
    return Mono.defer(
        () -> this.brokerRepository.findByBrokerCodeAndStoreIdAndIsDeleted(brokerDto.getBrokerCode(), mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED))
        .doOnNext(broker -> {
          throw new BusinessLogicException(EResponseCode.DUPLICATE_DATA.getCode(),
              EResponseCode.DUPLICATE_DATA.getMessage());
        })
        .switchIfEmpty(this.createBroker(mandatoryRequest, brokerDto))
        .flatMap(this.brokerRepository::save)
        .map(broker -> BeanMapperHelper.map(broker, BrokerDto.class));

  }

  @Override
  public Mono<BrokerDto> update(MandatoryRequest mandatoryRequest, BrokerDto brokerDto, Long id) {
    return this.findExist(mandatoryRequest, id)
        .flatMap(broker -> brokerRepository.findByBrokerCodeAndStoreIdAndIsDeletedAndIdNot(brokerDto.getBrokerCode(), mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED, id)
                .doOnNext(brokerDuplicate -> {
                  throw new BusinessLogicException(EResponseCode.DUPLICATE_DATA.getCode(),
                      EResponseCode.DUPLICATE_DATA.getMessage());
                })
                .switchIfEmpty(this.updateBroker(mandatoryRequest, brokerDto, broker))
                .flatMap(this.brokerRepository::save)
            .map(brokerSaved -> BeanMapperHelper.map(brokerSaved, BrokerDto.class))
        );
  }

  @Override
  public Mono<BrokerDto> delete(MandatoryRequest mandatoryRequest, Long id) {
    return this.findExist(mandatoryRequest, id)
        .doOnNext(broker -> {
          broker.setIsDeleted(CommonConstant.DELETED);
          broker.setUpdatedBy(mandatoryRequest.getUsername());
        })
        .flatMap(this.brokerRepository::save)
        .map(broker -> BeanMapperHelper.map(broker, BrokerDto.class));
  }

  private Mono<Broker> findExist(MandatoryRequest mandatoryRequest, Long id){
    return brokerRepository.findByIdAndStoreIdAndIsDeleted(id, mandatoryRequest.getStoreId(),
        CommonConstant.NOT_DELETED)
        .doOnError(throwable -> log.info("[{}] find broker id = {} error = {}", mandatoryRequest, id, throwable))
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        );
  }

  private Mono<? extends Broker> createBroker(MandatoryRequest mandatoryRequest, BrokerDto brokerDto){
    return Mono.defer(
        () -> {
          Broker broker =
              Broker.builder()
                  .storeId(mandatoryRequest.getStoreId())
                  .createdBy(mandatoryRequest.getUsername())
                  .brokerCode(brokerDto.getBrokerCode())
                  .brokerName(brokerDto.getBrokerName())
                  .build();
          return Mono.just(broker);
        });
  }

  private Mono<? extends Broker> updateBroker(MandatoryRequest mandatoryRequest, BrokerDto brokerDto, Broker broker){
    return Mono.defer(() -> {
      broker.setStoreId(mandatoryRequest.getStoreId());
      broker.setBrokerCode(brokerDto.getBrokerCode());
      broker.setBrokerName(brokerDto.getBrokerName());
      return Mono.just(broker);
    });
  }
}
