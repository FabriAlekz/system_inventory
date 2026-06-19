package com.fabrizioquispe.system_inventory.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fabrizioquispe.system_inventory.domain.constant.DataConstant;

import lombok.Data;

@Data
@Table(name = DataConstant.TABLE_ROLES, schema = DataConstant.SCHEMA_INVADMIN)
public class RolesEntity {
    @Id
    @Column("id_rol")
    private Integer idRol;
    @Column("type")
    private String type;
    @Column("description")
    private String description;
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("status")
    private Integer status;
}
