package com.fororoms.usuarios.controller;

import com.fororoms.usuarios.controller.dto.UserDTO;
import com.fororoms.usuarios.service.UsuarioService;
import com.fororoms.usuarios.service.domain.UserDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Obj Domain --> Obj DTO
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDomain userDomain = modelMapper.map(userDTO, UserDomain.class);
        usuarioService.actualizarUsuario(id, userDomain);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> obtenerTodosLosUsuarios() {
        List<UserDomain> userDomains = usuarioService.findAllUsuarios();
        List<UserDTO> userDTOS = userDomains.stream()
                .map(userDomain -> modelMapper.map(userDomain, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UserDomain> userDomain = usuarioService.obtenerUsuarioPorId(id);
        return userDomain.map(userDomain1 -> ResponseEntity.ok(modelMapper.map(userDomain1, UserDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<UserDTO> obtenerUsuarioPorUsername(@PathVariable String username) {
        Optional<UserDomain> userDomain = usuarioService.obtenerUsuarioByUsername(username);
        return userDomain.map(userDomain1 -> ResponseEntity.ok(modelMapper.map(userDomain1, UserDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
