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
@Table(name = DataConstant.TABLE_GRANTES, schema = DataConstant.SCHEMA_INVADMIN)
public class GrantesEntity {
    
    @Id
    @Column("id_grant")
    private Integer idGrant;

    @Column("type")
    private String type;

    @Column("description")
    private String description;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private Integer status; 
}
