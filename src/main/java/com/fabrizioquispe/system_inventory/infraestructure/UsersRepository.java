package com.fabrizioquispe.system_inventory.infraestructure;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fabrizioquispe.system_inventory.domain.entity.UsersEntity;

public interface UsersRepository extends ReactiveCrudRepository<UsersEntity,Integer> {
    
}
