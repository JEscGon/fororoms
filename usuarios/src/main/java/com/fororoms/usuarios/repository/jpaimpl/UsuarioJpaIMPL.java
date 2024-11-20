package com.fororoms.usuarios.repository.jpaimpl;

import com.fororoms.usuarios.repository.UserDetailsRepository;
import com.fororoms.usuarios.repository.UsuarioRepository;
import com.fororoms.usuarios.repository.entity.Rol;
import com.fororoms.usuarios.repository.entity.UserDetails;
import com.fororoms.usuarios.repository.entity.Usuario;
import com.fororoms.usuarios.repository.interfaces.IUsuario;
import com.fororoms.usuarios.service.domain.UserDetailsDomain;
import com.fororoms.usuarios.service.domain.UsuarioDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//· Obj dom --> obj entity
@Component
public class UsuarioJpaIMPL implements IUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void deleteUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }
    @Override
    public void save(@PathVariable Long id, UsuarioDomain usuarioDomain, UserDetailsDomain userDetailsDomain) {
        Usuario usuario;
        if(id != null) {
            Optional<Usuario> usuarioEntity = usuarioRepository.findById(id);
            if(usuarioEntity.isPresent()) {
                usuario = usuarioEntity.get();
                LocalDateTime aux = usuario.getFechaCreacion();
                usuario.setUsername(usuarioDomain.getUsername());
                usuario.setPassword(passwordEncoder.encode(usuarioDomain.getPassword()));
                usuario.setId(id);
                usuario.setRole(usuarioDomain.getRole().stream()
                    .map(roleDomain -> modelMapper.map(roleDomain, Rol.class))
                    .collect(Collectors.toSet()));
                usuario.setFechaEdicion(LocalDateTime.now());
                usuario.setFechaCreacion(aux);
                if(userDetailsDomain != null) {
                    UserDetails userDetails = modelMapper.map(userDetailsDomain, UserDetails.class);
                    userDetailsRepository.save(userDetails);
                    usuario.setUserDetails(userDetails);
                }
            } else {
                throw new RuntimeException("Usuario no encontrado");
            }
        }else{
            usuario = modelMapper.map(usuarioDomain, Usuario.class);
            usuario.setFechaCreacion(LocalDateTime.now());
            usuario.setUserDetails(modelMapper.map(userDetailsDomain, UserDetails.class));
        }
       if (userDetailsDomain != null) {
            UserDetails userDetails = modelMapper.map(userDetailsDomain, UserDetails.class);
            userDetailsRepository.save(userDetails);
            usuario.setUserDetails(userDetails);
       }
        usuarioRepository.save(usuario);
    }
    @Override
    public UsuarioDomain findUsuarioById(Long id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuarioEntity, UsuarioDomain.class);
    }
    @Override
    public List<UsuarioDomain> findAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDomain.class))
                .collect(Collectors.toList());
    }
    @Override
    public UsuarioDomain findUsuarioByUsername(String username) {
        Usuario usuarioEntity = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuarioEntity, UsuarioDomain.class);
    }

}
