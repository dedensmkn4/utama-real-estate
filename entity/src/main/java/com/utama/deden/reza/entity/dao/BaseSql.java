package com.utama.deden.reza.entity.dao;

import com.utama.deden.reza.entity.constant.fields.BaseSqlFields;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class BaseSql implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(value = BaseSqlFields.ID)
    private Long id;

    @Version
    @Column(value = BaseSqlFields.VERSION)
    private Long version;

    @Column(value = BaseSqlFields.STORE_ID)
    private String storeId;

    @Column(value= BaseSqlFields.IS_DELETED)
    private int isDeleted;

    @CreatedDate
    @Column(value= BaseSqlFields.CREATED_DATE)
    private LocalDate createdDate;

    @CreatedBy
    @Column(value= BaseSqlFields.CREATED_BY)
    private String createdBy;

    @LastModifiedDate
    @Column(value = BaseSqlFields.UPDATED_DATE)
    private LocalDate updatedDate;

    @LastModifiedBy
    @Column(value = BaseSqlFields.UPDATED_BY)
    private String updatedBy;

}
