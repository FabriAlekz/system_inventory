package com.fabrizioquispe.system_inventory.infraestructure;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fabrizioquispe.system_inventory.domain.entity.GrantesEntity;

@Repository
public interface GrantsRepository extends ReactiveCrudRepository<GrantesEntity, Integer> {
    
}
