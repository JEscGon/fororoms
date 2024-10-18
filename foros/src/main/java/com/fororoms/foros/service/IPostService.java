package com.fororoms.foros.service;

import com.fororoms.foros.entity.Post;

import java.util.List;

public interface IPostService {

    Post crearPost(Long foroId, Post post);

    Post obtenerPostPorId(Long id);

    List<Post> listarPostsPorForo(Long foroId);

    Post actualizarPost(Long id, Post post);

    void eliminarPost(Long id);

}
