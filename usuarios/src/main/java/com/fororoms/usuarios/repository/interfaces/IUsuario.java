package com.fororoms.usuarios.repository.interfaces;

import com.fororoms.usuarios.service.domain.UserDomain;

import java.util.List;

public interface IUsuario {
    void deleteUserById(Long id);
    void save(Long id, UserDomain usuarioDomain);
    <Optional>UserDomain findUserById(Long id);
    List<UserDomain> findAllUsers();
    <Optional>UserDomain findUserByUsername(String username);
}
