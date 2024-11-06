package com.fororoms.foros.controller.dto;

import com.fororoms.foros.repository.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {

    private Long id;
    private String contenido;

    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaEdicion;

    private Post post;
    private Long usuarioId;
}
