package com.fororoms.foros.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fororoms.foros.client.UsuariosClient;
import com.fororoms.foros.utils.JwtUtils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.AccessException;

@Component
public class CustomAuthenticationFilter implements Filter {

    @Autowired
    private UsuariosClient usuariosClient;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            String token = httpRequest.getHeader("Authorization");

            // TODO: VALIDAR TOKEN LLAMAR A USERS /validate-token (LLAMAR FEIGN CLIENT)
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7); // Remueve "Bearer "

                try {
                    // Llama al cliente Feign para validar el token
                    usuariosClient.validateToken(token);
                } catch (Exception ex) {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Código 401
                    httpResponse.getWriter().write("Error procesando la solicitud: " + ex.getMessage());
                    return; // Asegúrate de que no continúe al siguiente filtro
                }
            }

            // Continúa con el siguiente filtro en la cadena
            chain.doFilter(request, response);
    }


}