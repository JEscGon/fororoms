package com.fororoms.foros.service;

import com.fororoms.foros.entity.Foro;

import java.util.List;

public interface IForoService {

    Foro crearForo(Foro foro);

    Foro obtenerForoPorId(Long id);

    List<Foro> obtenerForos();

    Foro actualizarForo(Long id, Foro foro);

    void eliminarForo(Long id);

}
