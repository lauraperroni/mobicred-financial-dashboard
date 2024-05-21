package com.projetofinal.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.User.RegisterDTO;
import com.projetofinal.projetofinal.dtos.User.UserResponseDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/users")
public class ControllerUser {

    @Autowired
    private UserService service;

    @GetMapping("/all")
    public List<UserResponseDto> getAllUserDto() {
        return service.getAllUserDtoService();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserIdDto(@PathVariable Integer id) {
        return service.getUserIdDtoService(id);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        return service.registerUser(registerDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<String> postNewUser(@RequestBody User user) {
        return service.postNewUserService(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateUsuario(@PathVariable Integer id, @RequestBody User user) {
        return service.putUpdateUserService(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuarioId(@PathVariable Integer id) {
        return service.deleteUserIdService(id);
    }
}
