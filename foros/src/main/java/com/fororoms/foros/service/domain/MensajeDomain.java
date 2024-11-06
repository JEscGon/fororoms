package com.fororoms.foros.service.domain;

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
public class MensajeDomain {

    private Long id;
    private String contenido;

    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaEdicion;

    //TODO : ver como sacar solo el id de post.
    private PostDomain post;
    private Long usuarioId;
}