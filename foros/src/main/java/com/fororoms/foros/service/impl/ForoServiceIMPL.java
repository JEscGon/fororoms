package com.fororoms.foros.service.impl;

import com.fororoms.foros.entity.Foro;
import com.fororoms.foros.repository.ForoRepository;
import com.fororoms.foros.service.IForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForoServiceIMPL implements IForoService {

    @Autowired
    private ForoRepository foroRepository;

    @Override
    public Foro crearForo(Foro foro) {
        return foroRepository.save(foro);
    }

    @Override
    public Foro obtenerForoPorId(Long id) {
        return foroRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Foro> obtenerForos() {
        return foroRepository.findAll();
    }

    @Override
    public Foro actualizarForo(Long id, Foro foro) {
        Foro aux = obtenerForoPorId(id);
        aux.setTitulo(foro.getTitulo());
        aux.setPosts(foro.getPosts());
        return foroRepository.save(aux);
    }

    @Override
    public void eliminarForo(Long id) {
        foroRepository.deleteById(id);
    }

}
