package com.fabrizioquispe.system_inventory.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SuscriptionFilterDTO {
    private Integer idSuscription;
    private String typeSuscription;
    private BigDecimal price;
    private Integer durationDays;
    private Integer status;
    private LocalDateTime creationAt;
}
