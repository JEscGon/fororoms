package com.fororoms.foros.controller;


import com.fororoms.foros.controller.dto.ForoRequest;
import com.fororoms.foros.controller.dto.ForoDTO;
import com.fororoms.foros.service.domain.ForoDomain;
import com.fororoms.foros.service.ForoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Obj Domain --> Obj DTO
@RestController
@RequestMapping("/api/foros")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ForoDTO> crearForo(@RequestBody ForoRequest foroRequest) {
        ForoDomain foroDomain = modelMapper.map(foroRequest, ForoDomain.class);
        foroService.crearForo(foroDomain);
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
    public ResponseEntity<ForoDTO> actualizarForo(@PathVariable Long id, @RequestBody ForoRequest foroRequest) {
        ForoDomain foroDomain = modelMapper.map(foroRequest, ForoDomain.class);
        foroService.actualizarForo(id, foroDomain);
        ForoDTO foroDTO = modelMapper.map(foroDomain, ForoDTO.class);
        return ResponseEntity.ok(foroDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarForo(@PathVariable Long id) {
        foroService.eliminarForo(id);
        return ResponseEntity.ok(null);
    }

}
