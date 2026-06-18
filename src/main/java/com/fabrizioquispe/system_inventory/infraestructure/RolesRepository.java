package com.fabrizioquispe.system_inventory.infraestructure;

import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RolesRepository extends ReactiveCrudRepository<RolesEntity,Integer> {
}
