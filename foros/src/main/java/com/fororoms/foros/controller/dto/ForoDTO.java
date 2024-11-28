package com.fororoms.foros.controller.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForoDTO {

    private Long id;
    private String titulo;
    private Long usuarioId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

}