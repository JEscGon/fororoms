package com.fororoms.usuarios.controller;

import com.fororoms.usuarios.controller.dto.UsuarioDTO;
import com.fororoms.usuarios.controller.dto.UsuarioDetailsDTO;
import com.fororoms.usuarios.service.UsuarioService;
import com.fororoms.usuarios.service.domain.UserDetailsDomain;
import com.fororoms.usuarios.service.domain.UsuarioDomain;
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

    @GetMapping("/find/{username}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorNombre(@PathVariable String username) {
        Optional<UsuarioDomain> usuario = usuarioService.obtenerUsuarioPorNombre(username);
        if(usuario.isPresent()){
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            return ResponseEntity.ok(usuarioDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/findAll")
    public ResponseEntity <List<UsuarioDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList())
        );
    }
//TODO: revisar como se hace el mapeo de UserDetails y xke no guarda la informacion.
    @GetMapping("/findAll/details")
    public ResponseEntity <List<UsuarioDetailsDTO>> obtenerUsuariosDetails() {
        return ResponseEntity.ok(usuarioService.obtenerDetallesUsuarios().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDetailsDTO.class))
                .collect(Collectors.toList())
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDetailsDTO usuarioDetailsDTO) {
        UserDetailsDomain userDetailsDomain = modelMapper.map(usuarioDetailsDTO, UserDetailsDomain.class);
        UsuarioDomain usuarioDomain = usuarioService.obtenerUsuarioPorId(id).get();
        usuarioService.actualizarUsuario(id, usuarioDomain, userDetailsDomain);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioDTO usuarioDTO,UsuarioDetailsDTO usuarioDetailsDTO) {
        UsuarioDomain usuarioDomain = modelMapper.map(usuarioDTO, UsuarioDomain.class);
        UserDetailsDomain userDetailsDomain = modelMapper.map(usuarioDetailsDTO, UserDetailsDomain.class);
        usuarioService.crearUsuario(usuarioDomain, userDetailsDomain);
        return ResponseEntity.ok().build();
    }


}
