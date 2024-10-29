package com.fororoms.foros.service;

import com.fororoms.foros.controller.dto.ForoRequest;
import com.fororoms.foros.controller.dto.ForoResponse;
import com.fororoms.foros.service.domain.ForoDomain;

import java.util.List;

public interface IForoService {

    ForoDomain crearForo(ForoDomain foro);

    ForoDomain obtenerForoPorId(Long id);

    List<ForoDomain> obtenerForos();

    ForoDomain actualizarForo(Long id, ForoDomain foroRequest);

    void eliminarForo(Long id);


}
