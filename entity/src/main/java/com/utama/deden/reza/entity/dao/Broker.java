package com.utama.deden.reza.entity.dao;

import com.utama.deden.reza.entity.constant.TableName;
import com.utama.deden.reza.entity.constant.fields.BrokerFields;
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
@Table(value = TableName.BROKER)
public class Broker extends BaseSql {

  @Column(value = BrokerFields.BROKER_CODE)
  private String brokerCode;

  @Column(value = BrokerFields.BROKER_NAME)
  private String brokerName;

  @Builder
  public Broker(Long id, Long version, String storeId, int isDeleted, LocalDate createdDate,
      String createdBy, LocalDate updatedDate, String updatedBy, String brokerCode,
      String brokerName) {
    super(id, version, storeId, isDeleted, createdDate, createdBy, updatedDate, updatedBy);
    this.brokerCode = brokerCode;
    this.brokerName = brokerName;
  }

  @Override
  public boolean isNew() {
    return this.getId()==null;
  }

}
