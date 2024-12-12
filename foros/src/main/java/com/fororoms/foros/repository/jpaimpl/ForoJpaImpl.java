package com.fororoms.foros.repository.jpaimpl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fororoms.foros.repository.ForoRepository;
import com.fororoms.foros.repository.entity.Foro;
import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.service.domain.ForoDomain;
import com.fororoms.foros.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// obj dom --> obj entity
@Component
public class ForoJpaImpl implements IForo {

    @Autowired
    private ForoRepository foroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ForoDomain findForoById(Long id) {
        Foro foroEntity = foroRepository.findById(id).orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        return modelMapper.map(foroEntity, ForoDomain.class);
    }
    @Override
    public void deleteForoById(Long id) {
        foroRepository.deleteById(id);
    }

    @Override
    public ForoDomain save(String userId, Long id, ForoDomain foroDomain) {
        Foro foro;
        if (id != null) {
            Optional<Foro> foroEntity = foroRepository.findById(id);
            if (foroEntity.isPresent()) {
                foro = foroEntity.get();
                LocalDateTime aux = foro.getFechaCreacion();
                modelMapper.map(foroDomain, foro);
                foro.setFechaEdicion(LocalDateTime.now());
                foro.setFechaCreacion(aux);
                foro.setUsuarioId(Long.valueOf(userId));
                foro.setId(id);
            } else {
                throw new RuntimeException("Foro no encontrado");
            }
        } else {
            foro = modelMapper.map(foroDomain, Foro.class);
            foro.setFechaCreacion(LocalDateTime.now());
            foro.setUsuarioId(Long.valueOf(userId));
        }
        foroRepository.save(foro);
        return modelMapper.map(foro, ForoDomain.class);
    }
    @Override
    public List<ForoDomain> findAllForos() {
        List<Foro> foroEntities = foroRepository.findAll();
        return foroEntities.stream()
                .map(foro -> modelMapper.map(foro, ForoDomain.class))
                .collect(Collectors.toList());
    }
}
