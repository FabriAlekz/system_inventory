package com.fabrizioquispe.system_inventory.api.dto;

import java.util.List;

import com.fabrizioquispe.system_inventory.domain.entity.GrantesEntity;
import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsuariosDTO {
    private List<RolesEntity> roles;
    private List<GrantesEntity> grantes;
}
