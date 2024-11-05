package com.fororoms.foros.controller.dto;

import com.fororoms.foros.repository.entity.Foro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String contenido;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

    private Foro foro;

    private Long usuario_Id;
}
