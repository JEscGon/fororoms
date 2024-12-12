package com.fororoms.foros.repository.interfaces;

import com.fororoms.foros.service.domain.MensajeDomain;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface IMensaje {

    MensajeDomain save(String userId, Long postId, Long msgId, MensajeDomain mensajeDomain);
    MensajeDomain obtenerMensajePorId(Long id);
    List<MensajeDomain> listarMensajesPorPost(Long postId);
    void eliminarMensaje(Long id);

}
