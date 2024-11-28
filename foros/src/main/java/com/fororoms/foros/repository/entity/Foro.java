package com.fororoms.foros.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity(name = "foro")
@AllArgsConstructor
@NoArgsConstructor
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    // Relacionamos el foro con el usuario mediante el ID del usuario creador
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

    //Se ejecuta automáticamente antes de insertar un nuevo registro
    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }

    //Se ejecuta automáticamente antes de actualizar un registro existente
    @PreUpdate
    public void preUpdate() {
        fechaEdicion = LocalDateTime.now();
    }
}
