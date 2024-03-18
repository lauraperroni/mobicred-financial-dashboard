package com.projetofinal.projetofinal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.projetofinal.projetofinal.model.Categorias;
import com.projetofinal.projetofinal.repository.CategoriasRepository;

public class CategoriasService {

    // Repositório que vai conversar com o banco de dados
    @Autowired
    private CategoriasRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as categorias ===============================================
    public List<Categorias> getAllCategorias() {
        return repository.findAll();
    }

    // Traz uma categoria pelo id ================================================
    @SuppressWarnings("null")
    public Categorias getCategoriaId(Integer id) {
        return repository.findById(id).get();
    }

    // Adicionar novo usuário =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNovaCategoria(Categorias categoria) {
        repository.save(categoria);
        return ResponseEntity.ok("Categoria criada com sucesso.");
    }

    // Update de um usuário por id ============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateCategoria(Integer id, Categorias categoria) {
        if (repository.existsById(id)) {
            categoria.setId(id);
            repository.save(categoria);
            return ResponseEntity.ok("Categoria atualizada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Categoria não encontrada.");
        }
    }

    // Deletar um usuário por id =============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteCategoriaId(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Categoria deletada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Categoria não encontrada.");
        }
    }
}