package com.fabrizioquispe.system_inventory.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fabrizioquispe.system_inventory.domain.constant.DataConstant;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table(name = DataConstant.TABLE_USERS,schema = DataConstant.SCHEMA_INVADMIN)
public class UsersEntity {
    @Id
    @Column("id_user")
    private Integer idUser;

    @Column("id_sucursal")
    private Integer idSucursal;

    @Column("name")
    private String name;

    @Column("lastname")
    private String lastname;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("image_picture")
    private String imagePicture;

    @Column("status")
    private Integer status;

    @Column("create_at")
    private LocalDateTime createdAt;

    @Transient
    private List<SucursalesEntity> sucursales;

}
