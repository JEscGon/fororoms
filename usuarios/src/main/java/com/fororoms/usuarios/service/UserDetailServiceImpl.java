package com.fororoms.usuarios.service;


import com.fororoms.usuarios.controller.dto.AuthCreateUserRequest;
import com.fororoms.usuarios.controller.dto.AuthLoginRequest;
import com.fororoms.usuarios.controller.dto.AuthResponse;
import com.fororoms.usuarios.repository.entity.CustomUserDetails;
import com.fororoms.usuarios.repository.entity.Rol;
import com.fororoms.usuarios.repository.RolRepository;
import com.fororoms.usuarios.repository.UsuarioRepository;

import com.fororoms.usuarios.repository.entity.User;
import com.fororoms.usuarios.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RolRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " no encontrado"));
        // -- Configuración de roles --
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getRole()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority(role.getRoleEnum().name())));

        return new CustomUserDetails(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User Access OK", accessToken, true);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Usuario o contraseña incorrecta");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    public void createUser(AuthCreateUserRequest authCreateUserRequest) throws IllegalAccessException {

        String password = authCreateUserRequest.password();
        List<String> roleRequest = authCreateUserRequest.roleRequest().roleListName();
        Set<Rol> roleEntitySet = roleRepository.findByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if (roleEntitySet.isEmpty()) {
            throw new IllegalAccessException("No se encontraron roles");
        }
        User userEntity = User.builder()
                .username(authCreateUserRequest.username())
                .password(passwordEncoder.encode(authCreateUserRequest.password()))
                .role(roleEntitySet)
                .nombre(authCreateUserRequest.nombre())
                .apellidos(authCreateUserRequest.apellidos())
                .dni(authCreateUserRequest.dni())
                .email(authCreateUserRequest.email())
                .iban(authCreateUserRequest.iban())
                .ciudad(authCreateUserRequest.ciudad())
                .codigoPostal(authCreateUserRequest.codigoPostal())
                .pais(authCreateUserRequest.pais())
                .direccion(authCreateUserRequest.direccion())
                .telefono(authCreateUserRequest.telefono())
                .fechaCreacion(LocalDateTime.now())
                .fechaEdicion(null)
                .fechaNacimiento(authCreateUserRequest.fechaNacimiento())
                .build();

        userRepository.save(userEntity);
    }

    public void validateToken(String token) {
        jwtUtils.validateToken(token);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}