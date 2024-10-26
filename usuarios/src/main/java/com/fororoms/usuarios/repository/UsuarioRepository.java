package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findUserEntityByUsername(String username);
//    @Query("SELECT u FROM UserEntity u WHERE u.username = ?")     // -- QUERY NATIVO
//    Optional<UserEntity> findUser(String username);

}