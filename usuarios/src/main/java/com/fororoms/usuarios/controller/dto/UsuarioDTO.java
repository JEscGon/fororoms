package com.fororoms.usuarios.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private Boolean isEnabled;
    private Boolean accountNoExpired;
    private Boolean accountNoLocked;
    private Boolean credentialsNoExpired;

    private Set<RolDTO> role;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

    private UsuarioDetailsDTO usuarioDetails;
}
