package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.api.dto.GrantsFilterDTO;
import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.domain.entity.GrantesEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GrantsService {
    Flux<GrantesEntity> getGrants();
    Flux<GrantesEntity> filterGrants(GrantsFilterDTO filter);
    Mono<ResponseHandler<GrantesEntity>> createGrant(GrantesEntity grant);
    Mono<ResponseHandler<GrantesEntity>> updateGrant(Integer id, GrantesEntity grant);
    Mono<ResponseHandler<GrantesEntity>> deleteGrant(Integer id);
}
