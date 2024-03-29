package com.projetofinal.projetofinal.controller;

import com.projetofinal.projetofinal.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetofinal.projetofinal.dtos.User.AuthenticationDTO;
import com.projetofinal.projetofinal.dtos.User.LoginResponseDTO;
import com.projetofinal.projetofinal.infra.security.TokenService;
import com.projetofinal.projetofinal.service.AuthorizationService;
import com.projetofinal.projetofinal.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User data) {
        userService.postNewUserService(data);
        return ResponseEntity.ok().build();
    }
}