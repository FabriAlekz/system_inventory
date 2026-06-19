package com.fabrizioquispe.system_inventory.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.RolesFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;
import com.fabrizioquispe.system_inventory.infraestructure.RolesRepository;
import com.fabrizioquispe.system_inventory.service.RolesService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RolesImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public Flux<RolesEntity> findAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Mono<ResponseHandler<RolesEntity>> saveRole(RolesEntity role) {
        LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());

        return rolesRepository.save(role)
                .map(savedRole -> {
                    ResponseHandler<RolesEntity> response = new ResponseHandler<>();
                    response.setTimestamp(dt1);
                    response.setStatus(201);
                    response.setData(savedRole);
                    response.setMessage("Role saved successfully");
                    return response;
                });
    }

    @Override
    public Mono<ResponseHandler<RolesEntity>> updateRole(Integer id, RolesEntity role) {

        LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());

        return rolesRepository.findById(id)
                .flatMap(existingRole -> {
                    existingRole.setType(role.getType());
                    existingRole.setDescription(role.getDescription());
                    return rolesRepository.save(existingRole);
                })
                .map(updatedRole -> {
                    ResponseHandler<RolesEntity> response = new ResponseHandler<>();
                    response.setTimestamp(dt1);
                    response.setStatus(200);
                    response.setData(updatedRole);
                    response.setMessage("Role updated successfully");
                    return response;
                });
    }

    @Override
    public Mono<ResponseHandler<RolesEntity>> deleteRole(Integer id) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("ID cannot be null"));
        }
        LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());

        return rolesRepository.findById(id)
                .flatMap(existingRole -> rolesRepository.delete(existingRole)
                        .then(Mono.just(existingRole)))
                .map(deletedRole -> {
                    ResponseHandler<RolesEntity> response = new ResponseHandler<>();
                    response.setTimestamp(dt1);
                    response.setStatus(200);
                    response.setData(deletedRole);
                    response.setMessage("Role deleted successfully");
                    return response;
                });
    }

    @Override
    public Flux<RolesEntity> filterRoles(RolesFilterDTO filter) {
        return rolesRepository.findAll()
        .filter(rol ->
            (filter.getType() == null || Objects.equals(rol.getType(), filter.getType()))
            && (filter.getDescription() == null || Objects.equals(rol.getDescription(), filter.getDescription()))
            && (filter.getStatus() == null || Objects.equals(rol.getStatus(), filter.getStatus()))
            && (filter.getCreatedAt() == null || Objects.equals(rol.getCreatedAt(), filter.getCreatedAt()))
        );
    }

}
