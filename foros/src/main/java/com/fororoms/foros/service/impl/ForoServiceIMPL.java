package com.fororoms.foros.service.impl;


import com.fororoms.foros.repository.entity.Foro;
import com.fororoms.foros.repository.ForoRepository;
import com.fororoms.foros.service.IForoService;
import com.fororoms.foros.service.domain.ForoDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForoServiceIMPL implements IForoService {

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ForoDomain crearForo(ForoDomain foroDomain) {
        Foro foro = modelMapper.map(foroDomain, Foro.class);
        Foro foroGuardado = foroRepository.save(foro);
        return modelMapper.map(foroGuardado, ForoDomain.class);
    }

    @Override
    public ForoDomain obtenerForoPorId(Long id) {
        Foro foro =  foroRepository.findById(id).orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        return modelMapper.map(foro, ForoDomain.class);
    }

    @Override
    public List<ForoDomain> obtenerForos() {
        List<Foro> foros = foroRepository.findAll();
        return foros.stream()
                .map(foro -> modelMapper.map(foro, ForoDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public ForoDomain actualizarForo(Long id, ForoDomain foroDomain) {
        Foro foro = modelMapper.map(foroDomain, Foro.class);
        foro.setId(id);
        Foro foroActualizado = foroRepository.save(foro);
        return modelMapper.map(foroActualizado, ForoDomain.class);
    }

    @Override
    public void eliminarForo(Long id) {
        foroRepository.deleteById(id);
    }


}
