package com.example.examenfinalspringangular.service.impl;

import com.example.examenfinalspringangular.entity.Usuario;
import com.example.examenfinalspringangular.repository.UsuarioRepository;
import com.example.examenfinalspringangular.service.JwtUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public JwtUserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con username: " + username));

        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }
}