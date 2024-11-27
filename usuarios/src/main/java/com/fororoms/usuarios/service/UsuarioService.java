package com.fororoms.usuarios.service;

import com.fororoms.usuarios.repository.interfaces.IUsuario;
import com.fororoms.usuarios.service.domain.UserDomain;
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

    public Optional<UserDomain> obtenerUsuarioByUsername(String username) {
        return Optional.ofNullable(usuarioRepository.findUserByUsername(username));
    }
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteUserById(id);
    }
    public void actualizarUsuario(Long id, UserDomain usuarioDomain) {
        usuarioRepository.save(id ,usuarioDomain);
    }
    public List<UserDomain> findAllUsuarios() {
        return usuarioRepository.findAllUsers();
    }
    public Optional<UserDomain> obtenerUsuarioPorId(Long id) {
        return Optional.ofNullable(usuarioRepository.findUserById(id));
    }

}
