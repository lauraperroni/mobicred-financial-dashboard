package com.projetofinal.projetofinal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetofinal.projetofinal.model.Usuarios;
import com.projetofinal.projetofinal.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    // Endpoints
    // ========================================================================

    // Trazer todos os usuários
    public List<Usuarios> getAllUsuarios() {
        return repository.findAll();
    }

    // Traz um usuário pelo id
    @SuppressWarnings("null")
    public Usuarios getUsuarioId(Integer id) {
        return repository.findById(id).get();
    }

    // Adicionar novo usuário
    @SuppressWarnings("null")
    public ResponseEntity<String> postNovoUsuario(Usuarios usuario) {
        repository.save(usuario);
        return ResponseEntity.ok("Usuário criado com sucesso.");
    }

    // Update de um usuário por id
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateUsuario(Integer id, Usuarios usuario) {
        if (repository.existsById(id)) {
            usuario.setId(id);
            repository.save(usuario);
            return ResponseEntity.ok("Usuário atualizado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }
    }

    // Deletar um usuário por id
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteUsuarioId(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }
    }
}
