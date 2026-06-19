package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.RolesFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolesService {
    Flux<RolesEntity> findAllRoles();
    Flux<RolesEntity> filterRoles(RolesFilterDTO filter);
    Mono<ResponseHandler<RolesEntity>> saveRole(RolesEntity role);
    Mono<ResponseHandler<RolesEntity>> updateRole(Integer id, RolesEntity role);
    Mono<ResponseHandler<RolesEntity>> deleteRole(Integer id);
}
