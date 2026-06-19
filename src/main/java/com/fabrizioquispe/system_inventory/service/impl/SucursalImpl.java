package com.fabrizioquispe.system_inventory.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.SucursalFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;
import com.fabrizioquispe.system_inventory.infraestructure.SucursalRepository;
import com.fabrizioquispe.system_inventory.service.SucursalService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SucursalImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    @Override
    public Flux<SucursalesEntity> getAllSucursal() {
        return sucursalRepository.findAll();
    }

    @Override
    public Flux<SucursalesEntity> getFilterSucursal(SucursalFilterDTO filter) {
        return sucursalRepository.findAll()
                .filter(sucursal -> (filter.getIdSucursal() == null ||
                        filter.getIdSucursal().equals(sucursal.getIdSucursal()))
                        &&
                        (filter.getName() == null ||
                                sucursal.getName() != null &&
                                        sucursal.getName().toLowerCase()
                                                .contains(filter.getName().toLowerCase()))
                        &&
                        (filter.getDomain() == null ||
                                sucursal.getDomain() != null &&
                                        sucursal.getDomain().toLowerCase()
                                                .contains(filter.getDomain().toLowerCase()))
                        &&
                        (filter.getArea() == null ||
                                sucursal.getArea() != null &&
                                        sucursal.getArea().toLowerCase()
                                                .contains(filter.getArea().toLowerCase()))
                        &&
                        (filter.getIdSuscription() == null ||
                                filter.getIdSuscription().equals(sucursal.getIdSuscription()))
                        &&
                        (filter.getCreatedInitial() == null ||
                                filter.getCreatedInitial().equals(sucursal.getCreatedInitial()))
                        &&
                        (filter.getCreatedFinished() == null ||
                                filter.getCreatedFinished().equals(sucursal.getCreatedFinished()))
                        &&
                        (filter.getCreatedAt() == null ||
                                filter.getCreatedAt().equals(sucursal.getCreatedAt())));
    }

    @Override
    public Mono<ResponseHandler<SucursalesEntity>> createSucursal(SucursalesEntity sucursal) {
        return sucursalRepository.save(sucursal)
                .map(savedSucursal -> {
                    ResponseHandler<SucursalesEntity> response = new ResponseHandler<>();
                    response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
                    response.setStatus(201);
                    response.setData(savedSucursal);
                    response.setMessage("Sucursal created successfully");
                    return response;
                });
    }

    @Override
    public Mono<ResponseHandler<SucursalesEntity>> updateSucursal(Integer id, SucursalesEntity sucursal) {
        return sucursalRepository.findById(id)
                .flatMap(existingSucursal -> {
                    existingSucursal.setName(sucursal.getName());
                    existingSucursal.setDomain(sucursal.getDomain());
                    existingSucursal.setArea(sucursal.getArea());
                    existingSucursal.setIdSuscription(sucursal.getIdSuscription());
                    existingSucursal.setCreatedInitial(sucursal.getCreatedInitial());
                    existingSucursal.setCreatedFinished(sucursal.getCreatedFinished());
                    existingSucursal.setCreatedAt(sucursal.getCreatedAt());
                    return sucursalRepository.save(existingSucursal);
                })
                .map(updatedSucursal -> {
                    ResponseHandler<SucursalesEntity> response = new ResponseHandler<>();
                    response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
                    response.setStatus(200);
                    response.setData(updatedSucursal);
                    response.setMessage("Sucursal updated successfully");
                    return response;
                });
    }

    @Override
    public Mono<ResponseHandler<SucursalesEntity>> deleteSucursal(Integer id) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("ID cannot be null"));
        }
        return sucursalRepository.findById(id)
                .flatMap(existingSucursal -> sucursalRepository.delete(existingSucursal)
                        .then(Mono.just(existingSucursal)))
                .map(deletedSucursal -> {
                    ResponseHandler<SucursalesEntity> response = new ResponseHandler<>();
                    response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
                    response.setStatus(200);
                    response.setData(deletedSucursal);
                    response.setMessage("Sucursal deleted successfully");
                    return response;
                });
    }
}
