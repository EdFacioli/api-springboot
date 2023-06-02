package com.benice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benice.api.domain.Usuario;
import com.benice.api.dtos.TokenJWTDto;
import com.benice.api.dtos.UsuarioDto;
import com.benice.api.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioDto usuarioDto) {
        
        var authenticatorToken = new UsernamePasswordAuthenticationToken(usuarioDto.getLogin(), usuarioDto.getSenha());
        var authentication = authenticationManager.authenticate(authenticatorToken);
        var tokenJWT = tokenService.createToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
    }
}
