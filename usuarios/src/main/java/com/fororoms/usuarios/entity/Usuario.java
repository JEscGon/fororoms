package com.fororoms.usuarios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    // -- >> OBLIGATORIAS SPRING SECURITY
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "account_No_Expired")
    private Boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private Boolean accountNoLocked;

    @Column(name = "credentials_No_Expired")
    private Boolean credentialsNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Rol> role = new HashSet<>();

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    // -- >> FECHAS
    @Setter(AccessLevel.NONE)
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_edicion")
    private LocalDateTime fechaEdicion;

}
