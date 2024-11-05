package com.fororoms.foros.service.impl;


import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.service.domain.ForoDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//TODO : Eliminar interfaces de servicio

@Service
public class ForoService {

    @Autowired
    private IForo foroRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ForoDomain obtenerForoPorId(Long id) {
        ForoDomain foro =  foroRepository.findForoById(id);
        return modelMapper.map(foro, ForoDomain.class);
    }

    public ForoDomain crearForo(ForoDomain foroDomain) {
        ForoDomain foroGuardado = foroRepository.save(foroDomain);
        return modelMapper.map(foroGuardado, ForoDomain.class);
    }

    public List<ForoDomain> obtenerForos() {
        List<ForoDomain> foros = foroRepository.findAllForos();
        return foros.stream()
                .map(foro -> modelMapper.map(foro, ForoDomain.class))
                .collect(Collectors.toList());
    }

    public ForoDomain actualizarForo(Long id, ForoDomain foroDomain) {
        ForoDomain foroExistente = foroRepository.findForoById(id);
        modelMapper.map(foroDomain, foroExistente);
        ForoDomain foroActualizado = foroRepository.save(foroExistente);
        return modelMapper.map(foroActualizado, ForoDomain.class);
    }

    public void eliminarForo(Long id) {
        foroRepository.deleteForoById(id);
    }


}
