package com.example.examenfinalspringangular.controller;

import com.example.examenfinalspringangular.DTO.Auth.AuthRequest;
import com.example.examenfinalspringangular.DTO.Auth.AuthResponse;
import com.example.examenfinalspringangular.entity.Usuario;
import com.example.examenfinalspringangular.repository.UsuarioRepository;
import com.example.examenfinalspringangular.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private com.example.examenfinalspringangular.service.JwtUserDetailsService jwtUserDetailsService;

    /**
     * Endpoint para iniciar sesión y obtener un token JWT.
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrectos", e);
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthResponse(jwt);
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     */
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest authRequest) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            return "El usuario ya existe";
        }

        // Encriptar la contraseña antes de guardar el usuario
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(authRequest.getPassword());

        // Crear y guardar el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(authRequest.getUsername());
        usuario.setPassword(encryptedPassword);
        usuarioRepository.save(usuario);

        return "Usuario registrado exitosamente";
    }
}