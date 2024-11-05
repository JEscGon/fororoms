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
    private String autor;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

}