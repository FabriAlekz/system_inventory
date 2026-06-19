package com.fabrizioquispe.system_inventory.service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.SucursalFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SucursalService {
    Flux<SucursalesEntity>  getAllSucursal();
    Flux<SucursalesEntity> getFilterSucursal(SucursalFilterDTO filter);
    Mono<ResponseHandler<SucursalesEntity>> createSucursal(SucursalesEntity sucursal);
    Mono<ResponseHandler<SucursalesEntity>> updateSucursal(Integer id, SucursalesEntity sucursal);
    Mono<ResponseHandler<SucursalesEntity>> deleteSucursal(Integer id);
}
