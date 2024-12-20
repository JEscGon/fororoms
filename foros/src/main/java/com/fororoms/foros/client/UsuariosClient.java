package com.fororoms.foros.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "usuarios", url = "http://localhost:8002")
public interface UsuariosClient {

    @PostMapping("/auth/validate-token")
    ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String token);

}