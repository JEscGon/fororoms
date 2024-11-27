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
public class UserDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDateTime fechaNacimiento;
    private String dni;
    private String telefono;
    private String direccion;
    private String pais;
    private String codigoPostal;
    private String ciudad;
    private String iban;
    private String username;
    private String password;
    private Set<RolDTO> role;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;
}
