package com.fororoms.foros.service.impl;

import com.fororoms.foros.repository.entity.Mensaje;
import com.fororoms.foros.repository.entity.Post;
import com.fororoms.foros.repository.MensajeRepository;
import com.fororoms.foros.repository.PostRepository;
import com.fororoms.foros.service.IMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensajeServiceIMPL  implements IMensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Mensaje crearMensaje(Long postId, Mensaje mensaje) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post no encontrado")); // Excepcion si no se encuentra el post
        mensaje.setPost(post);
        return mensajeRepository.save(mensaje);
    }

    @Override
    public Mensaje obtenerMensajePorId(Long id) {
        return mensajeRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Mensaje no encontrado"));
    }

    @Override
    public List<Mensaje> listarMensajesPorPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));
        return mensajeRepository.findByPost(post);
    }

    @Override
    public Mensaje actualizarMensaje(Long id, Mensaje mensajeActualizado) {

        Mensaje mensajeExistente = obtenerMensajePorId(id);

        mensajeExistente.setContenido(mensajeActualizado.getContenido());
        mensajeExistente.setAutor(mensajeActualizado.getAutor());
        mensajeExistente.setFechaEdicion(LocalDateTime.now());

        return mensajeRepository.save(mensajeExistente);

    }

    @Override
    public void eliminarMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }

}
