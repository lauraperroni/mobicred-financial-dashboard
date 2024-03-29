package com.projetofinal.projetofinal.controller;

import com.projetofinal.projetofinal.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetofinal.projetofinal.dtos.User.AuthenticationDTO;
import com.projetofinal.projetofinal.service.AuthorizationService;
import com.projetofinal.projetofinal.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        authorizationService.loginService(data);
        return ResponseEntity.ok().body("");
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User data) {
        userService.postNewUserService(data);
        return ResponseEntity.ok().build();
    }
}