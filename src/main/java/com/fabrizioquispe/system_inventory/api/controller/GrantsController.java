package com.fabrizioquispe.system_inventory.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrizioquispe.system_inventory.api.dto.GrantsFilterDTO;
import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.domain.entity.GrantesEntity;
import com.fabrizioquispe.system_inventory.service.GrantsService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/grants")
@RequiredArgsConstructor
public class GrantsController {

    private final GrantsService grantsService;

    @GetMapping("/listar")
    public Flux<GrantesEntity> getGrants() {
        return grantsService.getGrants();
    }

    @GetMapping("/filter")
    public Flux<GrantesEntity> filterGrants(@ModelAttribute GrantsFilterDTO filter) {
        return grantsService.filterGrants(filter);
    }

    @PostMapping("/create")
    public Mono<ResponseHandler<GrantesEntity>> createGrant(@RequestBody GrantesEntity grant) {
        return grantsService.createGrant(grant);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseHandler<GrantesEntity>> updateGrant(@PathVariable Integer id,@RequestBody GrantesEntity grant) {
        return grantsService.updateGrant(id, grant);
    }

}
