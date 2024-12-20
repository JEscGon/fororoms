package com.fororoms.foros.controller;

import com.fororoms.foros.controller.dto.MensajeDTO;
import com.fororoms.foros.repository.entity.Mensaje;
import com.fororoms.foros.repository.interfaces.IMensaje;
import com.fororoms.foros.repository.interfaces.IPost;
import com.fororoms.foros.service.domain.MensajeDomain;
import com.fororoms.foros.service.domain.PostDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Obj Domain --> Obj DTO
@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private IMensaje mensajeService;
    @Autowired
    private IPost postService;
    @Autowired
    private ModelMapper modelMapper;

  //  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/new/{postId}")
    public ResponseEntity<MensajeDTO> crearMensaje(@RequestHeader("Authorization") String authorization, @PathVariable Long postId, @RequestBody MensajeDTO mensajeDTO) {
        PostDomain post = postService.obtenerPostPorId(postId);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        MensajeDomain mensaje = modelMapper.map(mensajeDTO, MensajeDomain.class);
        mensaje.setPost(post);
        mensajeService.save(authorization, postId, null, mensaje);
        MensajeDTO mensajeResponse = modelMapper.map(mensaje, MensajeDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensajeResponse);
    }

    @GetMapping("/{mensajeId}")
    public ResponseEntity<MensajeDTO> obtenerMensajePorId(@PathVariable Long mensajeId) {
        MensajeDomain mensaje = mensajeService.obtenerMensajePorId(mensajeId);
        MensajeDTO mensajeDTO = modelMapper.map(mensaje, MensajeDTO.class);
        return ResponseEntity.ok(mensajeDTO);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<MensajeDTO>> listarMensajesPorPost(@PathVariable Long postId) {
        List<MensajeDomain> mensajes = mensajeService.listarMensajesPorPost(postId);
        List<MensajeDTO> mensajeResponse = mensajes.stream()
                .map(mensajeDomain -> modelMapper.map(mensajeDomain, MensajeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mensajeResponse);
    }

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/{mensajeId}")
    public ResponseEntity<Mensaje> actualizarMensaje(@RequestHeader String authorization,@PathVariable Long mensajeId, @RequestBody MensajeDTO mensajeDTO) {
        MensajeDomain mensajeDomain = modelMapper.map(mensajeDTO, MensajeDomain.class);
        mensajeDomain.setPost(mensajeService.obtenerMensajePorId(mensajeId).getPost());
        mensajeService.save(authorization,null, mensajeId, mensajeDomain);
        mensajeDomain = mensajeService.save(authorization,null, mensajeId, mensajeDomain);
        MensajeDTO response = modelMapper.map(mensajeDomain, MensajeDTO.class);
        return ResponseEntity.ok(modelMapper.map(mensajeDomain, Mensaje.class));
    }

 //   @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{mensajeId}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long mensajeId) {
        mensajeService.eliminarMensaje(mensajeId);
        return ResponseEntity.noContent().build();
    }

}
