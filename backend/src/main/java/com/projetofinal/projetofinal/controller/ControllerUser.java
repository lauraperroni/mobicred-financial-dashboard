package com.projetofinal.projetofinal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.User.UserResponseDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")

public class ControllerUser {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private UserService service;

    // Endpoints
    // =============================================================================

    // Trazer todos os usuários pelo DTO ===========================================
    @GetMapping("/all")
    public List<UserResponseDto> getAllUserDto() {
        return service.getAllUserDtoService();
    }

    // Traz um usuário pelo id DTO =================================================
    @GetMapping("/{id}")
    public UserResponseDto getUserIdDto(@PathVariable Integer id) {
        return service.getUserIdDtoService(id);
    }

    // Adicionar novo usuário ======================================================
    @PostMapping("/new")
    public ResponseEntity<String> postNewUser(@RequestBody User user) {
        return service.postNewUserService(user);
    }

    // Update de um usuário por id =================================================
    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateUsuario(@PathVariable Integer id, @RequestBody User user) {
        return service.putUpdateUserService(id, user);
    }

    // Deletar um usuário por id ===================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuarioId(@PathVariable Integer id) {
        return service.deleteUserIdService(id);
    }
}