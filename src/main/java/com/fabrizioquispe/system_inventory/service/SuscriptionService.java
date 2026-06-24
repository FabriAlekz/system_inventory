package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.SuscriptionFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SuscriptionService {
    Flux<SuscriptionEntity> findAllSuscriptions();
    Flux<SuscriptionEntity> findAllSuscriptionFilter(SuscriptionFilterDTO suscriptionFilterDTO);

    Mono<ResponseHandler<SuscriptionEntity>> createSuscription(SuscriptionEntity suscriptionEntity);
    Mono<ResponseHandler<SuscriptionEntity>> updateSuscription(Integer idSuscription, SuscriptionEntity suscriptionEntity);
    Mono<ResponseHandler<SuscriptionEntity>> deleteSuscription(Integer idSuscription);
        
}
