package com.fororoms.foros.service.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MensajeDomain {
    private Long id;
    private String contenido;
    private String autor;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaEdicion;
    private Long postId;
    private Long usuarioId;
}