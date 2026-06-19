package com.fabrizioquispe.system_inventory.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fabrizioquispe.system_inventory.domain.constant.DataConstant;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table(name = DataConstant.TABLE_SUCURSALES, schema = DataConstant.SCHEMA_INVADMIN)
public class SucursalesEntity {
    @Id
    @Column("id_sucursal")
    private Integer idSucursal;
    
    @Column("name")
    private String name;

    @Column("domain")
    private String domain;

    @Column("area")
    private String area;

    @Column("id_suscription")
    private Integer idSuscription;

    @Column("created_initial")
    private LocalDateTime createdInitial;

    @Column("created_finished")
    private LocalDateTime createdFinished;

    @Column("created_at")
    private LocalDateTime createdAt;
}
