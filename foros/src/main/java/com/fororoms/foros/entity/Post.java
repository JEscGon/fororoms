package com.fororoms.foros.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String contenido;

    //@Setter(AccessLevel.NONE)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "foro_id", nullable = false)
    private Foro foro;

     // Almacenamos solo el ID del usuario, no la entidad Usuario (Feign Client para comunicación con el servicio de usuarios)
    private Long usuario_Id;

}
