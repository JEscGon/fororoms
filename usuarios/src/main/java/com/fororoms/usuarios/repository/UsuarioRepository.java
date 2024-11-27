package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


//    @Query("SELECT u FROM UserEntity u WHERE u.username = ?")     // -- QUERY NATIVO
//    Optional<UserEntity> findUser(String username);

}