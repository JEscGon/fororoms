package com.fororoms.usuarios.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDetailsDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String fechaNacimiento;
    private String dni;
    private String telefono;
    private String direccion;
    private String pais;
    private String codigoPostal;
    private String ciudad;
    private String iban;
    private UsuarioDTO userEntity;
}
