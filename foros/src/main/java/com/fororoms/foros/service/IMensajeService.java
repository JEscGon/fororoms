package com.fororoms.foros.service;

import com.fororoms.foros.entity.Mensaje;

import java.util.List;

public interface IMensajeService {

    Mensaje crearMensaje(Long postId, Mensaje mensaje);

    Mensaje obtenerMensajePorId(Long id);

    List<Mensaje> listarMensajesPorPost(Long postId);

    Mensaje actualizarMensaje(Long id, Mensaje mensaje);

    void eliminarMensaje(Long id);
}
