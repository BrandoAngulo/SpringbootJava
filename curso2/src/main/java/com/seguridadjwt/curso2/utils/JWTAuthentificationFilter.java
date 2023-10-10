package com.seguridadjwt.curso2.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Esta clase se utiliza para que cada vez que un usuario quiera logearse debe pasar por un filtro de autenticacion de JWT
@Component
@RequiredArgsConstructor
public class JWTAuthentificationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            //Estos tres parametros no deben ser nulos entonces se agrega la anotacion @NonNull de la libreria import org.springframework.lang.NonNull
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String usuarioCorreo;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        usuarioCorreo = jwtService.extractUsername(""); //TODO extrae el usuarioCorreo desde JWT token;
    }
}
