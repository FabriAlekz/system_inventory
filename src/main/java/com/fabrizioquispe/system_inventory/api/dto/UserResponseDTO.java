package com.fabrizioquispe.system_inventory.api.dto;

import java.util.List;

import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponseDTO {
    private Integer idUser;
    private Integer idSucursal;
    private String name;
    private String lastName;
    private String email;
    private String imagePicture;
    private Integer status;

    private List<RolesEntity> roles;
    private List<SucursalesEntity> sucursales;
}
