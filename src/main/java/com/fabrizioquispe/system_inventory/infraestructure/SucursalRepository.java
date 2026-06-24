package com.fabrizioquispe.system_inventory.infraestructure;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;


public interface SucursalRepository extends ReactiveCrudRepository<SucursalesEntity, Integer> {

}
