package com.example.examenfinalspringangular.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        // Envía un error 401 cuando el usuario no está autorizado
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado: El token JWT no es válido o falta.");
    }
}