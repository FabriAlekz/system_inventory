package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;

import reactor.core.publisher.Flux;

public interface Suscription {
    Flux<SuscriptionEntity> findAllSuscriptions();
        
}
