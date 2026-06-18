package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;

import reactor.core.publisher.Flux;

public interface RolesService {
    Flux<RolesEntity> findAllRoles();
}
