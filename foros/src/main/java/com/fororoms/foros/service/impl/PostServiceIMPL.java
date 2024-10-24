package com.fororoms.foros.service.impl;

import com.fororoms.foros.entity.Foro;
import com.fororoms.foros.entity.Post;
import com.fororoms.foros.repository.ForoRepository;
import com.fororoms.foros.repository.MensajeRepository;
import com.fororoms.foros.repository.PostRepository;
import com.fororoms.foros.service.IPostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceIMPL  implements IPostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ForoRepository foroRepository;
    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public Post crearPost(Long foroId, Post post) {
        Foro foro = foroRepository.findById(foroId).orElseThrow(
                () -> new RuntimeException("Foro no encontrado")
        );
        post.setForo(foro);
        post.setFechaCreacion(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post obtenerPostPorId(Long id) {
            return postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post no encontrado"));
    }

    @Override
    public List<Post> listarPostsPorForo(Long foroId) {
             Foro foro = foroRepository.findById(foroId)
            .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        return postRepository.findByForo(foro);
    }

    @Override
    public Post actualizarPost(Long id, Post postAct) {
        if (postAct.getTitulo() == null || postAct.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
       }
        if (postAct.getContenido() == null || postAct.getContenido().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        Post postExistente = obtenerPostPorId(id);
        Foro foro = postExistente.getForo();
        if (foro == null) {
            throw new RuntimeException("Foro no encontrado");
        }
        postExistente.setTitulo(postAct.getTitulo());
        postExistente.setContenido(postAct.getContenido());
        return postRepository.save(postExistente);
    }

    @Override
    @Transactional
    public void eliminarPost(Long id) {
        // Primero, elimina los mensajes relacionados
        mensajeRepository.deleteAllByPostId(id); // Asegúrate de tener este método en tu repositorio
        // Luego, elimina el post
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> listarPosts() {
        return postRepository.findAll();
    }
}
