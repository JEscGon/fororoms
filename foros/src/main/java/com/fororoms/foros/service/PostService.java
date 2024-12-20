package com.fororoms.foros.service;

import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.repository.interfaces.IPost;
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

    public void crearPost(String jwt,Long foroId, PostDomain post) {
        postRepository.save(jwt, foroId, null, post);
    }

    public PostDomain obtenerPostPorId(Long id) {
        return postRepository.obtenerPostPorId(id);
    }

    public List<PostDomain> listarPostsPorForo(Long foroId) {
        return postRepository.listarPostsPorForo(foroId);
    }

    public void actualizarPost(String jwt ,Long postId, PostDomain postAct) {
        postRepository.save(jwt, null, postId, postAct);
    }

    @Transactional
    public void eliminarPost(Long id) {
    // TODO: Primero, elimina los mensajes relacionados y luego elimina el post.
    //    mensajeRepository.deleteAllByPostId(id);
        postRepository.eliminarPost(id);
    }
    public List<PostDomain> listarPosts() {
        return postRepository.listarPosts();
    }
}
