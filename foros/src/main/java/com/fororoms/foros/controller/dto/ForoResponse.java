package com.fororoms.foros.controller.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForoResponse {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDateTime fechaCreacion;

    // Constructor
    public ForoResponse(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaCreacion = LocalDateTime.now();
    }



}