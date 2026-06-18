package com.fabrizioquispe.system_inventory.service.impl;

import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;
import com.fabrizioquispe.system_inventory.infraestructure.RolesRepository;
import com.fabrizioquispe.system_inventory.service.RolesService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class RolesImpl implements RolesService{

    private final RolesRepository rolesRepository;

    @Override
    public Flux<RolesEntity> findAllRoles() {
        return rolesRepository.findAll();
    }
    
}
