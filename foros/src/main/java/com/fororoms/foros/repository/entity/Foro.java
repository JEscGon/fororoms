package com.fororoms.foros.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String titulo;
    private String autor;
    private LocalDateTime fechaCreacion;
//    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts;

}
