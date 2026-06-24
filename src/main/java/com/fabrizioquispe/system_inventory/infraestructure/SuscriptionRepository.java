package com.fabrizioquispe.system_inventory.infraestructure;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;

public interface SuscriptionRepository extends ReactiveCrudRepository<SuscriptionEntity, Integer> {
    
}
