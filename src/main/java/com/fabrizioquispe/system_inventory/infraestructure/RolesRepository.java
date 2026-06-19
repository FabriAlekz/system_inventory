package com.fabrizioquispe.system_inventory.infraestructure;

import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends ReactiveCrudRepository<RolesEntity, Integer> {
}
