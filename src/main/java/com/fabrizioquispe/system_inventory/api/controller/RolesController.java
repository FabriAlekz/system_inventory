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
import com.fabrizioquispe.system_inventory.api.dto.RolesFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.RolesEntity;
import com.fabrizioquispe.system_inventory.service.RolesService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @GetMapping("/list")
    public Flux<RolesEntity> findAllRoles() {
        return rolesService.findAllRoles();
    }

    @PostMapping("/create")
    public Mono<ResponseHandler<RolesEntity>> saveRole(@RequestBody RolesEntity role){
        return rolesService.saveRole(role);
    }

    @GetMapping("/filter")
    public Flux<RolesEntity> filterRoles(@ModelAttribute RolesFilterDTO filter) {
        return rolesService.filterRoles(filter);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseHandler<RolesEntity>> updateRole(@PathVariable Integer id, @RequestBody RolesEntity role) {
        return rolesService.updateRole(id, role);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseHandler<RolesEntity>> deleteRole(@PathVariable Integer id) {
        return rolesService.deleteRole(id);
    }
}
