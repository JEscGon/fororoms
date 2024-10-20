package com.fororoms.foros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts;

}
