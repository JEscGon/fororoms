package com.fororoms.foros.controller;


import com.fororoms.foros.controller.dto.ForoDTO;
import com.fororoms.foros.service.domain.ForoDomain;
import com.fororoms.foros.service.ForoService;
import com.fororoms.foros.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Obj Domain --> Obj DTO
@RestController
@RequestMapping("/api/foros")
public class ForoController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ForoService foroService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
  //  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ForoDTO> crearForo(@RequestHeader("Authorization") String authorization, @RequestBody ForoDTO foroRequest) {
        ForoDomain foroDomain = modelMapper.map(foroRequest, ForoDomain.class);
        foroService.crearForo(authorization, foroDomain);
        ForoDTO foroDTO = modelMapper.map(foroDomain, ForoDTO.class);
        return ResponseEntity.ok(foroDTO);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ForoDTO> findForoById(@PathVariable Long id) {
        ForoDomain foroDomain = foroService.obtenerForoPorId(id);
        ForoDTO foroDTO = modelMapper.map(foroDomain, ForoDTO.class);
        return ResponseEntity.ok(foroDTO);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ForoDTO>> listarForos() {
        List<ForoDomain> foros = foroService.obtenerForos();
        List<ForoDTO> foroResponse = foros.stream()
                .map(foroDomain -> modelMapper.map(foroDomain, ForoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(foroResponse);
    }

    @PutMapping("/{id}")
  //  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ForoDTO> actualizarForo(@RequestHeader("Authorization") String authorization, @PathVariable Long id, @RequestBody ForoDTO foroRequest) {
        ForoDomain foroDomain = modelMapper.map(foroRequest, ForoDomain.class);
        foroService.actualizarForo(authorization, id, foroDomain);
        ForoDTO foroDTO = modelMapper.map(foroDomain, ForoDTO.class);
        return ResponseEntity.ok(foroDTO);
    }

    @DeleteMapping("/{id}")
  //  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarForo(@PathVariable Long id) {
        foroService.eliminarForo(id);
        return ResponseEntity.ok(null);
    }

}
