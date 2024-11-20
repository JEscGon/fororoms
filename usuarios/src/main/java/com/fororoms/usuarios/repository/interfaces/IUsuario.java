package com.fororoms.usuarios.repository.interfaces;

import com.fororoms.usuarios.service.domain.UserDetailsDomain;
import com.fororoms.usuarios.service.domain.UsuarioDomain;
import java.util.List;

public interface IUsuario {
    void deleteUsuarioById(Long id);
    void save(Long id, UsuarioDomain usuarioDomain, UserDetailsDomain userDetailsDomain);
    UsuarioDomain findUsuarioById(Long id);
    List<UsuarioDomain> findAllUsuarios();
    <Optional>UsuarioDomain findUsuarioByUsername(String username);

}
