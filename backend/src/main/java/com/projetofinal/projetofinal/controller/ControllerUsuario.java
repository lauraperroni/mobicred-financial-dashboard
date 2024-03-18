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

import com.projetofinal.projetofinal.dtos.UsuariosDto;
import com.projetofinal.projetofinal.model.Usuarios;
import com.projetofinal.projetofinal.service.UsuariosService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")

public class ControllerUsuario {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private UsuariosService service;

    // Endpoints
    // =============================================================================

    // Trazer todos os usuários pelo DTO ===========================================
    @GetMapping("/todos")
    public List<UsuariosDto> getTodosUsuariosDto() {
        return service.getAllUsuariosDto();
    }

    // Traz um usuário pelo id DTO =================================================
    @GetMapping("/{id}")
    public UsuariosDto getUsuarioIdDto(@PathVariable Integer id) {
        return service.getUsuarioIdDto(id);
    }

    // Adicionar novo usuário ======================================================
    @PostMapping("/novo")
    public ResponseEntity<String> postNovoUsuario(@RequestBody Usuarios usuario) {
        return service.postNovoUsuario(usuario);
    }

    // Update de um usuário por id =================================================
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> putUpdateUsuario(@PathVariable Integer id, @RequestBody Usuarios usuario) {
        return service.putUpdateUsuario(id, usuario);
    }

    // Deletar um usuário por id ===================================================
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteUsuarioId(@PathVariable Integer id) {
        return service.deleteUsuarioId(id);
    }
}