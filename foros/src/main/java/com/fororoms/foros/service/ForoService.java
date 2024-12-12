package com.fororoms.foros.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fororoms.foros.repository.interfaces.IForo;
import com.fororoms.foros.service.domain.ForoDomain;
import com.fororoms.foros.utils.JwtUtils;
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
    @Autowired
    private JwtUtils jwtUtils;

    public ForoDomain obtenerForoPorId(Long id) {
        return foroRepository.findForoById(id);
    }
    public void crearForo(String token, ForoDomain foroDomain) {
        DecodedJWT decodedJWT = jwtUtils.validateToken(token.replace("Bearer ", ""));
        String userId = jwtUtils.extractUserId(decodedJWT);
        foroRepository.save(userId, null, foroDomain);
    }
    public List<ForoDomain> obtenerForos() {
        return foroRepository.findAllForos();
    }
    public void actualizarForo(String token, Long id, ForoDomain foroDomain) {
        DecodedJWT decodedJWT = jwtUtils.validateToken(token.replace("Bearer ", ""));
        String userId = jwtUtils.extractUserId(decodedJWT);
        foroRepository.save(userId, id, foroDomain);
    }
    public void eliminarForo(Long id) {
        foroRepository.deleteForoById(id);
    }

}
