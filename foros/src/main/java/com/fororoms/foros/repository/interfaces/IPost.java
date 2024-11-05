package com.fororoms.foros.repository.interfaces;

import com.fororoms.foros.service.domain.PostDomain;

import java.util.List;

public interface IPost {

    PostDomain obtenerPostPorId(Long id);

    void eliminarPost(Long id);
    PostDomain save(Long foroId,Long postId, PostDomain post);
    List<PostDomain> listarPosts();
    List<PostDomain> listarPostsPorForo(Long foroId);


}
