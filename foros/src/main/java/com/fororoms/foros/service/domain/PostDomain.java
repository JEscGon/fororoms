package com.fororoms.foros.service.domain;

import com.fororoms.foros.repository.entity.Foro;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDomain {

    private Long id;
    private String contenido;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

    private ForoDomain foro;

    private Long usuarioId;
}
