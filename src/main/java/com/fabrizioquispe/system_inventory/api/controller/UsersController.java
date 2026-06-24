package com.fabrizioquispe.system_inventory.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.UserResponseDTO;
import com.fabrizioquispe.system_inventory.domain.entity.UsersEntity;
import com.fabrizioquispe.system_inventory.service.UsersService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/list")
    public Flux<UsersEntity> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/create")
    public Mono<ResponseHandler<UserResponseDTO>> createUser(@RequestBody UsersEntity user) {
        return usersService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseHandler<UserResponseDTO>> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody UsersEntity user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseHandler<UserResponseDTO>> deleteUser(
            @PathVariable("id") Integer id) {
        return usersService.deleteUser(id);
    }
}