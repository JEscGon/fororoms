package com.fororoms.foros.repository.interfaces;

import com.fororoms.foros.service.domain.PostDomain;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface IPost {

    PostDomain obtenerPostPorId(Long id);

    void eliminarPost(Long id);
    PostDomain save(@RequestHeader("Authorization") String token, Long foroId, Long postId, PostDomain post);
    List<PostDomain> listarPosts();
    List<PostDomain> listarPostsPorForo(Long foroId);


}
