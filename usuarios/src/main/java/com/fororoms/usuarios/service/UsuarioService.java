package com.fororoms.usuarios.service;

import com.fororoms.usuarios.repository.interfaces.IUsuario;
import com.fororoms.usuarios.service.domain.UserDetailsDomain;
import com.fororoms.usuarios.service.domain.UsuarioDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private IUsuario usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public void crearUsuario(UsuarioDomain usuarioDomain, UserDetailsDomain userDetailsDomain) {
        usuarioRepository.save(null, null,userDetailsDomain );
    }
    public Optional<UsuarioDomain> obtenerUsuarioPorNombre(String username) {
        return Optional.ofNullable(usuarioRepository.findUsuarioByUsername(username));
    }
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteUsuarioById(id);
    }
    public void actualizarUsuario(Long id, UsuarioDomain usuarioDomain, UserDetailsDomain userDetailsDomain) {
        usuarioRepository.save(id ,usuarioDomain, userDetailsDomain);
    }
    public List<UsuarioDomain> obtenerUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }
    public Optional<UsuarioDomain> obtenerUsuarioPorId(Long id) {
        return Optional.ofNullable(usuarioRepository.findUsuarioById(id));
    }



}
