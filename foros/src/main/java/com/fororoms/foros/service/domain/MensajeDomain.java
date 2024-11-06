package com.fororoms.foros.service.domain;

import com.fororoms.foros.repository.entity.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MensajeDomain {

    private Long id;
    private String contenido;

    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaEdicion;

    //TODO : ver como sacar solo el id de post.
    private Post post;
    private Long usuarioId;
}