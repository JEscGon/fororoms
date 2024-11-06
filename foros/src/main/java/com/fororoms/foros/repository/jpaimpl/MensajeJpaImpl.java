package com.fororoms.foros.repository.jpaimpl;

import com.fororoms.foros.repository.MensajeRepository;
import com.fororoms.foros.repository.entity.Mensaje;
import com.fororoms.foros.repository.interfaces.IMensaje;
import com.fororoms.foros.service.domain.MensajeDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MensajeJpaImpl implements IMensaje {

    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MensajeDomain save(Long postId, Long msgId, MensajeDomain mensajeDomain) {
        Mensaje mensaje;
        if(msgId != null){
            Optional<Mensaje> mensajeEntity = mensajeRepository.findById(msgId);
            if(mensajeEntity.isPresent()) {
                mensaje = mensajeEntity.get();
                LocalDateTime aux = mensaje.getFechaPublicacion();
                modelMapper.map(mensajeDomain, mensaje);
                mensaje.setId(msgId);
                mensaje.setFechaEdicion(LocalDateTime.now());
                mensaje.setFechaPublicacion(aux);
            }else{
                throw new RuntimeException("Mensaje no encontrado");
            }
        }else{
            mensaje = modelMapper.map(mensajeDomain, Mensaje.class);
            mensaje.setFechaPublicacion(LocalDateTime.now());
        }
        mensajeRepository.save(mensaje);
        return modelMapper.map(mensaje, MensajeDomain.class);
    }

    @Override
    public MensajeDomain obtenerMensajePorId(Long id) {
        Mensaje msgEntity = mensajeRepository.findById(id).orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        return modelMapper.map(msgEntity, MensajeDomain.class);
    }

    @Override
    public List<MensajeDomain> listarMensajesPorPost(Long postId) {
        List<Mensaje> msgEntityList = mensajeRepository.findAllByPostId(postId);
        return msgEntityList.stream()
          .map(msg -> modelMapper.map(msg, MensajeDomain.class))
          .collect(Collectors.toList());
    }



    @Override
    public void eliminarMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }

}
