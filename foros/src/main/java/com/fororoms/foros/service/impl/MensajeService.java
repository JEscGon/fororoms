package com.fororoms.foros.service.impl;

import com.fororoms.foros.repository.interfaces.IMensaje;
import com.fororoms.foros.service.domain.MensajeDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private IMensaje mensajeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void save(Long postId, MensajeDomain mensaje) {
        mensajeRepository.save(postId,null , mensaje);
    }

    public MensajeDomain obtenerMensajePorId(Long id) {
        return mensajeRepository.obtenerMensajePorId(id);
    }

    public List<MensajeDomain> listarMensajesPorPost(Long postId) {
        return mensajeRepository.listarMensajesPorPost(postId);
    }

    public MensajeDomain actualizarMensaje(Long idMsg, MensajeDomain mensajeActualizado) {
        return mensajeRepository.save(null,idMsg, mensajeActualizado);
    }

    public void eliminarMensaje(Long id) {
        mensajeRepository.eliminarMensaje(id);
    }

}
