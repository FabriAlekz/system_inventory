package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.UserResponseDTO;
import com.fabrizioquispe.system_inventory.domain.entity.UsersEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {
    Flux<UsersEntity> getAllUsers();
    Mono<ResponseHandler<UserResponseDTO>> createUser(UsersEntity user);
    Mono<ResponseHandler<UserResponseDTO>> updateUser(Integer id, UsersEntity user);
    Mono<ResponseHandler<UserResponseDTO>> deleteUser(Integer id);
}
