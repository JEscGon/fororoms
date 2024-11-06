package com.fororoms.foros.controller;

import com.fororoms.foros.controller.dto.MensajeDTO;
import com.fororoms.foros.repository.entity.Mensaje;
import com.fororoms.foros.repository.entity.Post;
import com.fororoms.foros.repository.interfaces.IMensaje;
import com.fororoms.foros.repository.interfaces.IPost;
import com.fororoms.foros.service.IMensajeService;
import com.fororoms.foros.service.domain.MensajeDomain;
import com.fororoms.foros.service.domain.PostDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/post/{postId}")
    public ResponseEntity<MensajeDTO> crearMensaje(@PathVariable Long postId, @RequestBody MensajeDTO mensajeDTO) {
        PostDomain post = postService.obtenerPostPorId(postId);
        if(post == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        MensajeDomain mensaje = modelMapper.map(mensajeDTO, MensajeDomain.class);
        mensaje.setPost(post);
        mensajeService.save(postId,null, mensaje);
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

    @PutMapping("/{mensajeId}")
    public ResponseEntity<Mensaje> actualizarMensaje(@PathVariable Long mensajeId, @RequestBody MensajeDTO mensajeDTO) {
        MensajeDomain mensajeDomain = modelMapper.map(mensajeDTO, MensajeDomain.class);
        mensajeDomain.setPost(mensajeService.obtenerMensajePorId(mensajeId).getPost());
        mensajeService.save(null, mensajeId, mensajeDomain);
        mensajeDomain = mensajeService.save(null, mensajeId, mensajeDomain);
        MensajeDTO response = modelMapper.map(mensajeDomain, MensajeDTO.class);
        return ResponseEntity.ok(modelMapper.map(mensajeDomain, Mensaje.class));
    }

    @DeleteMapping("/{mensajeId}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long mensajeId) {
        mensajeService.eliminarMensaje(mensajeId);
        return ResponseEntity.noContent().build();
    }


}
