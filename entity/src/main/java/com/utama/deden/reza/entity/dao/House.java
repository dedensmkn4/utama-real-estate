package com.utama.deden.reza.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utama.deden.reza.entity.constant.TableName;
import com.utama.deden.reza.entity.constant.enums.EOfferType;
import com.utama.deden.reza.entity.constant.enums.EStatus;
import com.utama.deden.reza.entity.constant.fields.HouseFields;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Table(value = TableName.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class House extends BaseSql{

  @Column(value = HouseFields.OFFER_TYPE)
  private EOfferType offerType;

  @Column(value = HouseFields.OFFER_COST)
  private double offerCost;

  @Column(value = HouseFields.AREA_SQUARE_FOOT)
  private double areaSquareFoot;

  @Column(value = HouseFields.ADDRESS)
  private String address;

  @Column(value = HouseFields.STREET)
  private String street;

  @Column(value = HouseFields.CITY)
  private String city;

  @Column(value = HouseFields.STATUS)
  private EStatus status;

  @Column(value = HouseFields.BROKER_ID)
  private Long brokerId;

  @Builder
  public House(Long id, Long version, String storeId, int isDeleted, LocalDate createdDate,
      String createdBy, LocalDate updatedDate, String updatedBy,
      EOfferType offerType, double offerCost, double areaSquareFoot, String address,
      String street, String city, EStatus status, Long brokerId) {
    super(id, version, storeId, isDeleted, createdDate, createdBy, updatedDate, updatedBy);
    this.offerType = offerType;
    this.offerCost = offerCost;
    this.areaSquareFoot = areaSquareFoot;
    this.address = address;
    this.street = street;
    this.city = city;
    this.status = status;
    this.brokerId = brokerId;
  }


  @Override
  public boolean isNew() {
    return this.getId()==null;
  }
}
