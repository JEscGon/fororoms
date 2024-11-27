package com.fororoms.usuarios.repository.jpaimpl;

import com.fororoms.usuarios.repository.UserDetailsRepository;
import com.fororoms.usuarios.repository.UsuarioRepository;
import com.fororoms.usuarios.repository.entity.User;
import com.fororoms.usuarios.repository.interfaces.IUsuario;
import com.fororoms.usuarios.service.domain.UserDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
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
    public void deleteUserById(Long id) {
        usuarioRepository.deleteById(id);
    }
    @Override
    public void save(@PathVariable Long id, UserDomain usuarioDomain) {
        if(id!=null){
            User usuarioEntity = usuarioRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Usuario no encontrado"));
            usuarioEntity.setUsername(usuarioDomain.getUsername());
            usuarioEntity.setPassword(passwordEncoder.encode(usuarioDomain.getPassword()));
            usuarioEntity.setDni(usuarioDomain.getDni());
            usuarioEntity.setNombre(usuarioDomain.getNombre());
            usuarioEntity.setApellidos(usuarioDomain.getApellidos());
            usuarioEntity.setFechaNacimiento(usuarioDomain.getFechaNacimiento());
            usuarioEntity.setEmail(usuarioDomain.getEmail());
            usuarioEntity.setTelefono(usuarioDomain.getTelefono());
            usuarioEntity.setDireccion(usuarioDomain.getDireccion());
            usuarioEntity.setPais(usuarioDomain.getPais());
            usuarioEntity.setCodigoPostal(usuarioDomain.getCodigoPostal());
            usuarioEntity.setCiudad(usuarioDomain.getCiudad());
            usuarioEntity.setIban(usuarioDomain.getIban());
            usuarioEntity.setFechaCreacion(usuarioDomain.getFechaCreacion());
            usuarioEntity.setFechaEdicion(LocalDateTime.now());

            usuarioRepository.save(usuarioEntity);
        }
    }
    @Override
    public UserDomain findUserById(Long id) {
        User usuarioEntity = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuarioEntity, UserDomain.class);
    }
    @Override
    public List<UserDomain> findAllUsers() {
        List<User> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UserDomain.class))
                .collect(Collectors.toList());
    }
    @Override
    public UserDomain findUserByUsername(String username) {
        User usuarioEntity = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuarioEntity, UserDomain.class);
    }

}
