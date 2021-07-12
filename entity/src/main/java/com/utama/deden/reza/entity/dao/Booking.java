package com.utama.deden.reza.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utama.deden.reza.entity.constant.TableName;
import com.utama.deden.reza.entity.constant.fields.BookingFields;
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
@Table(value = TableName.BOOKING)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking extends BaseSql{

  @Column(value = BookingFields.CUSTOMER_NAME)
  private String customerName;

  @Column(value = BookingFields.CUSTOMER_ADDRESS)
  private String customerAddress;

  @Column(value = BookingFields.FINAL_PRICE)
  private double finalPrice;

  @Column(value = BookingFields.HOUSE_ID)
  private Long houseId;

  @Builder
  public Booking(Long id, Long version, String storeId, int isDeleted,
      LocalDate createdDate, String createdBy, LocalDate updatedDate, String updatedBy,
      String customerName, String customerAddress, double finalPrice, Long houseId) {
    super(id, version, storeId, isDeleted, createdDate, createdBy, updatedDate, updatedBy);
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    this.finalPrice = finalPrice;
    this.houseId = houseId;
  }

  @Override
  public boolean isNew() {
    return this.getId()==null;
  }
}
