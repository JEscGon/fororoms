package com.fororoms.foros.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;

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

    @Column(nullable = false, length = 500) // Longitud máxima y no nulo
    private String contenido;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foro_id", nullable = false)
    private Foro foro;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @ElementCollection
    @CollectionTable(name = "post_likes", joinColumns = @JoinColumn(name = "post_id"))
    @MapKeyColumn(name = "usuario_id")
    @Column(name = "liked")
    private Map<Long, Boolean> likes;

    @ElementCollection
    @CollectionTable(name = "post_dislikes", joinColumns = @JoinColumn(name = "post_id"))
    @MapKeyColumn(name = "usuario_id")
    @Column(name = "disliked")
    private Map<Long, Boolean> dislikes;

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaEdicion = LocalDateTime.now();
    }
}
