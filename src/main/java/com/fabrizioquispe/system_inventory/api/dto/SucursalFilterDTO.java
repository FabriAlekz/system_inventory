package com.fabrizioquispe.system_inventory.api.dto;

import java.time.LocalDateTime;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SucursalFilterDTO {
    private Integer idSucursal;
    private String name;
    private String domain;
    private String area;
    private Integer idSuscription;
    private LocalDateTime createdInitial;
    private LocalDateTime createdFinished;
    private LocalDateTime createdAt;
}
