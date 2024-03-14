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
import com.projetofinal.projetofinal.model.Usuarios;
import com.projetofinal.projetofinal.service.UsuariosService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")

public class ControllerUsuario {

    // Cria a dependencia do repositório pra conversar com banco de dados
    @Autowired
    private UsuariosService service;

    // Endpoints
    // =============================================================================

    // Trazer todos os usuários
    @GetMapping("/all")
    public List<Usuarios> getTodosUsuarios(Usuarios usuarios) {
        return service.getAllUsuarios();
    }

    // Traz um usuário pelo id
    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public Usuarios getUsuarioId(@PathVariable Integer id) {
        return service.getUsuarioId(id);
    }

    // Adicionar novo usuário
    @SuppressWarnings("null")
    @PostMapping("/novo")
    public ResponseEntity<String> postNovoUsuario(@RequestBody Usuarios usuario) {
        return service.postNovoUsuario(usuario);
    }

    // Update de um usuário por id
    @SuppressWarnings("null")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> putUpdateUsuario(@PathVariable Integer id, @RequestBody Usuarios usuario) {
        return service.putUpdateUsuario(id, usuario);
    }

    // Deletar um usuário por id
    @SuppressWarnings("null")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteUsuarioId(@PathVariable Integer id) {
        return service.deleteUsuarioId(id);
    }
}