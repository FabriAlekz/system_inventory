package com.fabrizioquispe.system_inventory.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.fabrizioquispe.system_inventory.api.dto.GrantsFilterDTO;
import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.domain.entity.GrantesEntity;
import com.fabrizioquispe.system_inventory.infraestructure.GrantsRepository;
import com.fabrizioquispe.system_inventory.service.GrantsService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GrantsImpl implements GrantsService {

    private final GrantsRepository grantsRepository;

    @Override
    public Flux<GrantesEntity> getGrants() {
        return grantsRepository.findAll();
    }

    @Override
    public Flux<GrantesEntity> filterGrants(GrantsFilterDTO filter) {
        return grantsRepository.findAll()
                .filter(grant -> (filter.getType() == null || (grant.getType() != null && grant.getType().contains(filter.getType())))
                 && (filter.getDescription() == null || (grant.getDescription() != null && grant.getDescription().contains(filter.getDescription()))));
    }

    @Override
    public Mono<ResponseHandler<GrantesEntity>> createGrant(GrantesEntity grant) {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        return grantsRepository.save(grant)
        .map(savedGrant -> {
            ResponseHandler<GrantesEntity> response = new ResponseHandler<>();
            response.setTimestamp(now);
            response.setStatus(201);
            response.setData(savedGrant);
            response.setMessage("Grant created successfully");
            return response;
        });
    }

    @Override
    public Mono<ResponseHandler<GrantesEntity>> updateGrant(Integer id, GrantesEntity grant) {
        return grantsRepository.findById(id).flatMap(existingGrant -> {
            existingGrant.setType(grant.getType());
            existingGrant.setDescription(grant.getDescription());
            return grantsRepository.save(existingGrant);
        }).map(updatedGrant -> {
            ResponseHandler<GrantesEntity> response = new ResponseHandler<>();
            response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
            response.setStatus(200);
            response.setData(updatedGrant);
            response.setMessage("Grant updated successfully");
            return response;
        });
    }

    @Override
    public Mono<ResponseHandler<GrantesEntity>> deleteGrant(Integer id) {
        if (id == null) {
            ResponseHandler<GrantesEntity> response = new ResponseHandler<>();
            response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
            response.setStatus(400);
            response.setMessage("ID cannot be null");
            return Mono.just(response);
        }
        return grantsRepository.findById(id)
        .flatMap(existingGrant -> grantsRepository.delete(existingGrant)
        .then(Mono.just(existingGrant)))
        .map(deletedGrant -> {
            ResponseHandler<GrantesEntity> response = new ResponseHandler<>();
            response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
            response.setStatus(200);
            response.setData(deletedGrant);
            response.setMessage("Grant deleted successfully");
            return response;
        });
    }

}
