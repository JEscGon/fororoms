package com.fororoms.foros.repository.jpaimpl;

import com.fororoms.foros.repository.ForoRepository;
import com.fororoms.foros.repository.entity.Foro;
import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.service.domain.ForoDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//** MAPPER , repository foro
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
    public ForoDomain updateForoById(Long id,ForoDomain foroDomain) {
        Foro foroEntity = foroRepository.findById(id).orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        return modelMapper.map(foroEntity, ForoDomain.class);
    }

    @Override
    public ForoDomain save(ForoDomain foroDomain) {
        Foro foroEntity = modelMapper.map(foroDomain, Foro.class);
        Foro foroGuardado = foroRepository.save(foroEntity);
        return modelMapper.map(foroGuardado, ForoDomain.class);
    }

    @Override
    public List<ForoDomain> findAllForos() {
        List<Foro> foroEntities = foroRepository.findAll();
        return foroEntities.stream()
                .map(foro -> modelMapper.map(foro, ForoDomain.class))
                .collect(Collectors.toList());
    }
}
