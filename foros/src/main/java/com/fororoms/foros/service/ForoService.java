package com.fororoms.foros.service;

import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.service.domain.ForoDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ForoService {

    @Autowired
    private IForo foroRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ForoDomain obtenerForoPorId(Long id) {
        return foroRepository.findForoById(id);
    }
    public void crearForo(ForoDomain foroDomain) {
        foroRepository.save(null, foroDomain);
    }
    public List<ForoDomain> obtenerForos() {
        return foroRepository.findAllForos();
    }
    public void actualizarForo(Long id, ForoDomain foroDomain) {
        foroRepository.save(id, foroDomain);
    }
    public void eliminarForo(Long id) {
        foroRepository.deleteForoById(id);
    }

}
