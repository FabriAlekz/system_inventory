package com.fabrizioquispe.system_inventory.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.SucursalFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;
import com.fabrizioquispe.system_inventory.service.SucursalService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sucursal")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @GetMapping("/listar")
    public Flux<SucursalesEntity> getAllSucursal() {
        return sucursalService.getAllSucursal();
    }

    @GetMapping("/filter")
    public Flux<SucursalesEntity> getFilterSucursal(@ModelAttribute SucursalFilterDTO filter) {
        return sucursalService.getFilterSucursal(filter);
    }

    @PostMapping("/create")
    public Mono<ResponseHandler<SucursalesEntity>> createSucursal(@RequestBody SucursalesEntity sucursal){
        return sucursalService.createSucursal(sucursal);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseHandler<SucursalesEntity>> updateSucursal(@PathVariable Integer id, @RequestBody SucursalesEntity sucursal) {
        return sucursalService.updateSucursal(id, sucursal);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseHandler<SucursalesEntity>> deleteSucursal(@PathVariable Integer id) {
        return sucursalService.deleteSucursal(id);
    }
}
