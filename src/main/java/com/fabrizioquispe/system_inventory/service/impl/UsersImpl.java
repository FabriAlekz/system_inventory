package com.fabrizioquispe.system_inventory.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.UserResponseDTO;
import com.fabrizioquispe.system_inventory.domain.builder.UsuariosBuilder;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;
import com.fabrizioquispe.system_inventory.domain.entity.UsersEntity;
import com.fabrizioquispe.system_inventory.infraestructure.SucursalRepository;
import com.fabrizioquispe.system_inventory.infraestructure.UsersRepository;
import com.fabrizioquispe.system_inventory.service.UsersService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsersImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final SucursalRepository sucursalRepository;
    private final UsuariosBuilder usuariosBuilder;

    @Override
    public Flux<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Mono<ResponseHandler<UserResponseDTO>> createUser(UsersEntity user) {

        if (user.getSucursales() == null || user.getSucursales().isEmpty()) {
            return Mono.just(usuariosBuilder.buildError(400, "At least one branch is required"));
        }

        List<Integer> idsRequest = user.getSucursales().stream().map(SucursalesEntity::getIdSucursal).toList();

        return sucursalRepository
                .findAllById(idsRequest)
                .collectList()
                .flatMap(sucursales -> {


                    if (sucursales.size() != user.getSucursales().size()) {
                        return Mono.just(usuariosBuilder.buildError(400, "One or more branches do not exist"));
                    }

                    if (user.getCreatedAt() == null) {
                        user.setCreatedAt(LocalDateTime.now());
                    }
                    if (user.getStatus() == null) {
                        user.setStatus(1);
                    }

                    return usersRepository.save(user)
                            .map(savedUser -> usuariosBuilder.buildSuccess(savedUser, sucursales));
                })
                .onErrorResume(error -> {
                    return Mono.just(usuariosBuilder.buildError(500, "Internal error while creating user"));
                });
    }

    @Override
    public Mono<ResponseHandler<UserResponseDTO>> updateUser(Integer id, UsersEntity user) {
        return usersRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setLastname(user.getLastname());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setImagePicture(user.getImagePicture());
                    existingUser.setStatus(user.getStatus());

                    return usersRepository.save(existingUser)
                            .map(savedUser -> usuariosBuilder.buildSuccess(savedUser, null));
                })
                .switchIfEmpty(Mono.just(usuariosBuilder.buildError(404, "User not found")))
                .onErrorResume(error -> Mono.just(usuariosBuilder.buildError(500, "Internal error while updating user")));
    }

    @Override
    public Mono<ResponseHandler<UserResponseDTO>> deleteUser(Integer id) {
        return usersRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setStatus(0);
                    return usersRepository.save(existingUser)
                            .map(savedUser -> usuariosBuilder.buildSuccess(savedUser, null));
                })
                .switchIfEmpty(Mono.just(usuariosBuilder.buildError(404, "User not found")))
                .onErrorResume(error -> Mono.just(usuariosBuilder.buildError(500, "Internal error while deleting user")));
    }

}
