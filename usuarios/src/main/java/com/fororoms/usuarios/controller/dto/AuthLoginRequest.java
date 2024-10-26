package com.fororoms.usuarios.controller.dto;

import org.springframework.lang.NonNull;

public record AuthLoginRequest(@NonNull String username,@NonNull String password) {}
