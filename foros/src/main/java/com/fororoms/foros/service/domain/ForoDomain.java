package com.fororoms.foros.service.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForoDomain {
    private Long id;
    private String titulo;
    private String autor;
    private LocalDateTime fechaCreacion;
}
