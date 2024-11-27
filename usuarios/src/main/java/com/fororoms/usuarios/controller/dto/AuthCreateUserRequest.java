package com.fororoms.usuarios.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AuthCreateUserRequest(@NotBlank String username, @NotBlank String password , @Valid AuthCreateRoleRequest roleRequest ,
                                    String nombre, String apellidos, String email, LocalDateTime fechaNacimiento ,
                                    String dni, String telefono,String direccion,String pais,
                                    String codigoPostal,String ciudad,String iban ) {}
