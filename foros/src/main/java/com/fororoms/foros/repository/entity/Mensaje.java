package com.fororoms.foros.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "mensaje")
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenido;

    //TODO : Sacar el autor del token

    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaEdicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    @Column(name = "usuario_id")
    private Long usuarioId;
}
