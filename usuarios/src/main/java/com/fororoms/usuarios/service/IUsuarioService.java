package com.fororoms.usuarios.service;

import com.fororoms.usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService  {

    Usuario crearUsuario(Usuario usuario);

    Optional<Usuario> obtenerUsuarioPorNombre(String username);

    void eliminarUsuario(Long id);

    Usuario actualizarUsuario(Long id, Usuario usuario);

    List<Usuario> obtenerUsuarios();



}
