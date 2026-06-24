package com.fabrizioquispe.system_inventory.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.SuscriptionFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;
import com.fabrizioquispe.system_inventory.service.SuscriptionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/suscriptions")
@RequiredArgsConstructor
public class SuscriptionController {

    private final SuscriptionService suscriptionService;

    @GetMapping("/list")
    public Flux<SuscriptionEntity> findAllSuscriptions() {
        return suscriptionService.findAllSuscriptions();
    }

    @GetMapping("/filter")
    public Flux<SuscriptionEntity> findAllSuscriptionFilter(SuscriptionFilterDTO filter) {
        return suscriptionService.findAllSuscriptionFilter(filter);
    }

    @PostMapping("/create")
    public Mono<ResponseHandler<SuscriptionEntity>> createSuscription(@RequestBody SuscriptionEntity suscription) {
        return suscriptionService.createSuscription(suscription);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseHandler<SuscriptionEntity>> updateSuscription(Integer idSuscription, @RequestBody SuscriptionEntity suscription) {
        return suscriptionService.updateSuscription(idSuscription, suscription);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseHandler<SuscriptionEntity>> deleteSuscription(Integer idSuscription) {
        return suscriptionService.deleteSuscription(idSuscription);
    }
}
