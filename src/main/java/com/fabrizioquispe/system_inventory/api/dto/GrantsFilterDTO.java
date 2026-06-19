package com.fabrizioquispe.system_inventory.api.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GrantsFilterDTO {
    private Integer idGrant;
    private String type;
    private String description;
    private LocalDateTime createdAt;
    private Integer status;
}
