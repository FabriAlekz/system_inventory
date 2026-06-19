package com.fabrizioquispe.system_inventory.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fabrizioquispe.system_inventory.domain.constant.DataConstant;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table(name = DataConstant.TABLE_SUSCRIPTION, schema = DataConstant.SCHEMA_INVADMIN)
public class SuscriptionEntity {
    @Id
    @Column("id_suscription")
    private Integer idSuscription;
    @Column("type_suscription")
    private String typeSuscription;
    @Column("price")
    private BigDecimal price;
    @Column("duration_days")
    private Integer durationDays;
    @Column("status")
    private Integer status;
    @Column("creation_at")
    private LocalDateTime creationAt;
}
