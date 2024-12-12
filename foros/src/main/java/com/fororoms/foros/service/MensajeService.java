package com.fororoms.foros.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fororoms.foros.repository.interfaces.IMensaje;
import com.fororoms.foros.service.domain.MensajeDomain;
import com.fororoms.foros.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private IMensaje mensajeRepository;

    public void save(String jwt ,Long postId, MensajeDomain mensaje) {
        mensajeRepository.save(jwt,postId,null , mensaje);
    }

    public MensajeDomain obtenerMensajePorId(Long id) {
        return mensajeRepository.obtenerMensajePorId(id);
    }

    public List<MensajeDomain> listarMensajesPorPost(Long postId) {
        return mensajeRepository.listarMensajesPorPost(postId);
    }

    public MensajeDomain actualizarMensaje(String jwt ,Long idMsg, MensajeDomain mensajeActualizado) {
        return mensajeRepository.save(jwt,null,idMsg, mensajeActualizado);
    }

    public void eliminarMensaje(Long id) {
        mensajeRepository.eliminarMensaje(id);
    }

}
