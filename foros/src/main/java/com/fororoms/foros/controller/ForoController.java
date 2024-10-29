package com.fororoms.foros.controller;


import com.fororoms.foros.controller.dto.ForoRequest;
import com.fororoms.foros.controller.dto.ForoResponse;
import com.fororoms.foros.service.IForoService;
import com.fororoms.foros.service.domain.ForoDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foros")
public class ForoController {

    @Autowired
    private IForoService foroService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ForoResponse> crearForo(@RequestBody ForoRequest foroRequest) {
        ForoDomain foroDomain = modelMapper.map(foroRequest, ForoDomain.class);
        ForoDomain nuevoForo = foroService.crearForo(foroDomain);
        ForoResponse foroResponse = modelMapper.map(nuevoForo, ForoResponse.class);
        return ResponseEntity.ok(foroResponse);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ForoResponse> findForo(@PathVariable Long id) {
        ForoDomain foroDomain = foroService.obtenerForoPorId(id);
        ForoResponse foroResponse = modelMapper.map(foroDomain, ForoResponse.class);
        return ResponseEntity.ok(foroResponse);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ForoResponse>> listarForos() {
        List<ForoDomain> foros = foroService.obtenerForos();
        List<ForoResponse> foroResponses = foros.stream()
                .map(foroDomain -> modelMapper.map(foroDomain, ForoResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(foroResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForoResponse> actualizarForo(@PathVariable Long id, @RequestBody ForoRequest foroRequest) {
        ForoDomain foroDomain = modelMapper.map(foroRequest, ForoDomain.class);
        ForoDomain foroActualizado = foroService.actualizarForo(id, foroDomain);
        ForoResponse foroResponse = modelMapper.map(foroActualizado, ForoResponse.class);
        return ResponseEntity.ok(foroResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarForo(@PathVariable Long id) {
        foroService.eliminarForo(id);
        return ResponseEntity.ok(null);
    }


}
