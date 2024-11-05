package com.fororoms.foros.service.impl;

import com.fororoms.foros.repository.entity.Post;
import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.repository.interfaces.IPost;
import com.fororoms.foros.service.domain.ForoDomain;
import com.fororoms.foros.service.domain.PostDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private IPost postRepository;
    @Autowired
    private IForo foroRepository;

    public void crearPost(Long foroId, PostDomain post) {
        ForoDomain foro = foroRepository.findForoById(foroId);
    }
    public PostDomain obtenerPostPorId(Long id) {
        return postRepository.obtenerPostPorId(id);
    }
    public List<PostDomain> listarPostsPorForo(Long foroId) {
        return postRepository.listarPostsPorForo(foroId);
    }
    public void actualizarPost(Long id, PostDomain postAct) {
        postRepository.save(null,id ,postAct );
    }
    @Transactional
    public void eliminarPost(Long id) {
    // Primero, elimina los mensajes relacionados
//        mensajeRepository.deleteAllByPostId(id);
    // Luego, elimina el post
        postRepository.eliminarPost(id);
    }
    public List<PostDomain> listarPosts() {
        return postRepository.listarPosts();
    }
}
