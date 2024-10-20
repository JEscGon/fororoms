package com.fororoms.foros.controller;

import com.fororoms.foros.entity.Mensaje;
import com.fororoms.foros.service.IMensajeService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private IMensajeService mensajeService;

    // Crear un nuevo mensaje en un post
    @PostMapping("/post/{postId}")
    public ResponseEntity<Mensaje> crearMensaje(@PathVariable Long postId, @RequestBody Mensaje mensaje) {
        Mensaje nuevoMensaje = mensajeService.crearMensaje(postId, mensaje);
        return ResponseEntity.ok(nuevoMensaje);
    }

     // Obtener un mensaje por ID
    @GetMapping("/{mensajeId}")
    public ResponseEntity<Mensaje> obtenerMensajePorId(@PathVariable Long mensajeId) {
        Mensaje mensaje = mensajeService.obtenerMensajePorId(mensajeId);
        return ResponseEntity.ok(mensaje);
    }

    // Listar todos los mensajes de un post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Mensaje>> listarMensajesPorPost(@PathVariable Long postId) {
        List<Mensaje> mensajes = mensajeService.listarMensajesPorPost(postId);
        return ResponseEntity.ok(mensajes);
    }

    // Actualizar un mensaje por ID
    @PutMapping("/{mensajeId}")
    public ResponseEntity<Mensaje> actualizarMensaje(@PathVariable Long mensajeId, @RequestBody Mensaje mensaje) {
        Mensaje mensajeActualizado = mensajeService.actualizarMensaje(mensajeId, mensaje);
        return ResponseEntity.ok(mensajeActualizado);
    }

    // Eliminar un mensaje por ID
    @DeleteMapping("/{mensajeId}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long mensajeId) {
        mensajeService.eliminarMensaje(mensajeId);
        return ResponseEntity.noContent().build();
    }


}
