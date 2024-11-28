package com.fororoms.foros.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity(name = "mensaje")
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500) // Longitud máxima y no nulo
    private String contenido;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    private LocalDateTime fechaEdicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @ElementCollection
    @CollectionTable(name = "mensaje_likes", joinColumns = @JoinColumn(name = "mensaje_id"))
    @MapKeyColumn(name = "usuario_id")
    @Column(name = "liked")
    private Map<Long, Boolean> likes;

    @ElementCollection
    @CollectionTable(name = "mensaje_dislikes", joinColumns = @JoinColumn(name = "mensaje_id"))
    @MapKeyColumn(name = "usuario_id")
    @Column(name = "disliked")
    private Map<Long, Boolean> dislikes;

    @PrePersist
    public void prePersist() {
        fechaPublicacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaEdicion = LocalDateTime.now();
    }
}
