package com.fororoms.foros.controller;


import com.fororoms.foros.entity.Foro;
import com.fororoms.foros.service.IForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foros")
public class ForoController {

    @Autowired
    private IForoService foroService;

    @PostMapping
    public ResponseEntity<Foro> crearForo(@RequestBody Foro foro) {
        Foro nuevoForo = foroService.crearForo(foro);
        return ResponseEntity.ok(nuevoForo);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Foro> findForo(@PathVariable Long id) {
        Foro foro = foroService.obtenerForoPorId(id);
        return ResponseEntity.ok(foro);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Foro>> listarForos() {
        List<Foro> foros = foroService.obtenerForos();
        return ResponseEntity.ok(foros);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Foro> actualizarForo(@PathVariable Long id, @RequestBody Foro foro) {
        Foro foroActualizado = foroService.actualizarForo(id, foro);
        return ResponseEntity.ok(foroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarForo(@PathVariable Long id) {
        foroService.eliminarForo(id);
        return ResponseEntity.ok(null);
    }


}
