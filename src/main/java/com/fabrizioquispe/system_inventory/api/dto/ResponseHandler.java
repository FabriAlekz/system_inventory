package com.fabrizioquispe.system_inventory.api.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResponseHandler<T> {
    private LocalDateTime timestamp;
    private int status;
    private T data;
    private String message;
}
