package com.fororoms.foros.repository.interfaces;

import com.fororoms.foros.service.domain.MensajeDomain;

import java.util.List;

public interface IMensaje {

    MensajeDomain save(Long postId,Long msgId, MensajeDomain mensajeDomain);
    MensajeDomain obtenerMensajePorId(Long id);
    List<MensajeDomain> listarMensajesPorPost(Long postId);
    void eliminarMensaje(Long id);

}
