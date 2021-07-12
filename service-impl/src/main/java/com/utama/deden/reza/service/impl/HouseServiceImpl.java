package com.utama.deden.reza.service.impl;

import com.utama.deden.reza.dao.api.BrokerRepository;
import com.utama.deden.reza.dao.api.HouseRepository;
import com.utama.deden.reza.dao.api.HouseRepositoryCustom;
import com.utama.deden.reza.entity.common.MandatoryRequest;
import com.utama.deden.reza.entity.constant.CommonConstant;
import com.utama.deden.reza.entity.constant.enums.EResponseCode;
import com.utama.deden.reza.entity.dao.Broker;
import com.utama.deden.reza.entity.dao.House;
import com.utama.deden.reza.entity.dto.BrokerDto;
import com.utama.deden.reza.entity.dto.HouseDto;
import com.utama.deden.reza.libraries.exception.BusinessLogicException;
import com.utama.deden.reza.libraries.utility.BeanMapperHelper;
import com.utama.deden.reza.service.api.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class HouseServiceImpl implements HouseService {

  private final HouseRepository houseRepository;
  private final HouseRepositoryCustom houseRepositoryCustom;
  private final BrokerRepository brokerRepository;

  public HouseServiceImpl(HouseRepository houseRepository,
      HouseRepositoryCustom houseRepositoryCustom,
      BrokerRepository brokerRepository) {
    this.houseRepository = houseRepository;
    this.houseRepositoryCustom = houseRepositoryCustom;
    this.brokerRepository = brokerRepository;
  }


  @Override
  public Mono<Page<HouseDto>> findAll(MandatoryRequest mandatoryRequest, Pageable page) {
    return houseRepositoryCustom.findHousePaginatedCustom(mandatoryRequest.getStoreId(), page, CommonConstant.NOT_DELETED)
        .collectList()
        .map(houseDtoList -> new PageImpl<>(houseDtoList, page, houseDtoList.size()));
  }

  @Override
  public Mono<HouseDto> findById(MandatoryRequest mandatoryRequest, Long id) {
    return this.findExist(mandatoryRequest, id);
  }

  @Override
  public Mono<HouseDto> create(MandatoryRequest mandatoryRequest, HouseDto houseDto) {
    return brokerRepository.findByBrokerCodeAndStoreIdAndIsDeleted(houseDto.getBroker().getBrokerCode(), mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED)
        .flatMap(broker -> houseRepository.save(this.toHouse(mandatoryRequest, houseDto, broker))
            .map(house -> BeanMapperHelper.map(house, HouseDto.class))
            .doOnNext(houseDtoSaved -> houseDtoSaved.setBroker(BeanMapperHelper.map(broker, BrokerDto.class)))
        )
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        );
  }

  @Override
  public Mono<HouseDto> update(MandatoryRequest mandatoryRequest, HouseDto houseDto,
      Long id) {
    return houseRepository.findByIdAndStoreIdAndIsDeleted(id, mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED)
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        )
        .flatMap(house -> this.findExistBroker(mandatoryRequest, houseDto.getBroker())
            .map(broker -> this.toHouse(mandatoryRequest, houseDto, broker))
            .doOnNext(houseForUpdate -> {
              houseForUpdate.setId(house.getId());
              houseForUpdate.setVersion(house.getVersion());
            })
        )
        .doOnNext(house -> log.info("[{}] update house house = {}", mandatoryRequest, house))
        .flatMap(houseRepository::save)
        .flatMap(house -> this.findExist(mandatoryRequest, id));
  }

  @Override
  public Mono<HouseDto> delete(MandatoryRequest mandatoryRequest, Long id) {
    return houseRepository.findByIdAndStoreIdAndIsDeleted(id, mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED)
        .doOnNext(house -> {
          house.setIsDeleted(CommonConstant.DELETED);
          house.setUpdatedBy(mandatoryRequest.getUsername());
          log.info("house = {}",house);
        })
        .flatMap(houseRepository::save)
        .map(house -> BeanMapperHelper.map(house, HouseDto.class))
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        );
  }

  private Mono<HouseDto> findExist(MandatoryRequest mandatoryRequest, Long id){
    return houseRepositoryCustom.findHouseCustom(mandatoryRequest.getStoreId(), id, CommonConstant.NOT_DELETED)
        .doOnError(throwable -> log.info("[{}] find home id = {} error = {}", mandatoryRequest, id, throwable))
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        );
  }

  private Mono<Broker> findExistBroker(MandatoryRequest mandatoryRequest, BrokerDto brokerDto){
    return brokerRepository.findByBrokerCodeAndStoreIdAndIsDeleted(brokerDto.getBrokerCode(), mandatoryRequest.getStoreId(), CommonConstant.NOT_DELETED)
        .switchIfEmpty(Mono.error(
            new BusinessLogicException(EResponseCode.DATA_NOT_EXIST.getCode(),
                EResponseCode.DATA_NOT_EXIST.getMessage()))
        );
  }

  private House toHouse(MandatoryRequest mandatoryRequest, HouseDto houseDto, Broker broker){
    return House.builder()
        .offerType(houseDto.getOfferType())
        .offerCost(houseDto.getOfferCost())
        .areaSquareFoot(houseDto.getAreaSquareFoot())
        .address(houseDto.getAddress())
        .city(houseDto.getCity())
        .street(houseDto.getStreet())
        .status(houseDto.getStatus())
        .storeId(mandatoryRequest.getStoreId())
        .brokerId(broker.getId())
        .build();
  }

}
