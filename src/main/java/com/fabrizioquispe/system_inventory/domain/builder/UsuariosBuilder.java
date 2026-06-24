package com.fabrizioquispe.system_inventory.domain.builder;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.UserResponseDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;
import com.fabrizioquispe.system_inventory.domain.entity.UsersEntity;

@Component
public class UsuariosBuilder {
    public ResponseHandler<UserResponseDTO> buildSuccess(UsersEntity savedUser,
            List<SucursalesEntity> sucursales) {

        UserResponseDTO dto = new UserResponseDTO();
        dto.setIdUser(savedUser.getIdUser());
        dto.setName(savedUser.getName());
        dto.setLastName(savedUser.getLastname());
        dto.setEmail(savedUser.getEmail());
        dto.setImagePicture(savedUser.getImagePicture());
        dto.setStatus(savedUser.getStatus());
        dto.setSucursales(sucursales);

        ResponseHandler<UserResponseDTO> response = new ResponseHandler<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(201);
        response.setData(dto);
        response.setMessage("Usuario creado exitosamente");
        return response;
    }

    public ResponseHandler<UserResponseDTO> buildError(int status, String message) {
        ResponseHandler<UserResponseDTO> response = new ResponseHandler<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status);
        response.setData(null);
        response.setMessage(message);
        return response;
    }
}
