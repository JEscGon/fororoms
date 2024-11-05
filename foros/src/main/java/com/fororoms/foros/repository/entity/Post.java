package com.fororoms.foros.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


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
    private String contenido;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

    //TODO : Agregar likes y dislikes a los posts

    @ManyToOne
    @JoinColumn(name = "foro_id", nullable = false)
    private Foro foro;
    // Almacenamos solo el ID del usuario, no la entidad Usuario (Feign Client para comunicación con el servicio de usuarios)
    @Column(name = "usuario_id")
    private Long usuarioId;
}
