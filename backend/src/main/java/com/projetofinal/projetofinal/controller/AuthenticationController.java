package com.projetofinal.projetofinal.controller;

import com.projetofinal.projetofinal.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetofinal.projetofinal.dtos.User.AuthenticationDTO;
import com.projetofinal.projetofinal.dtos.User.RegisterDTO;
import com.projetofinal.projetofinal.repository.User.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        @SuppressWarnings("unused")
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        // verificar se o email não existe no banco
        if (this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        // se não existe, cadastra ela com o hash de senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
