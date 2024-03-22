package com.projetofinal.projetofinal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetofinal.projetofinal.dtos.Category.CategoryRequestDto;
import com.projetofinal.projetofinal.model.Category.Category;
import com.projetofinal.projetofinal.repository.Category.CategoryRepository;

@Service
public class CategoryService {

    // Repositório que vai conversar com o banco de dados
    @Autowired
    private CategoryRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as categorias ===============================================
    public List<Category> getAllCategoryService() {
        return repository.findAll();
    }

    // Traz uma categoria pelo id ================================================
    @SuppressWarnings("null")
    public Category getCategoryIdService(Integer id) {
        return repository.findById(id).get();
    }

    // Adicionar novo usuário =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewCategoryService(Category category) {
        repository.save(category);
        return ResponseEntity.ok("New category created.");
    }

    // Update de um usuário por id ============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateCategoryService(Integer id, Category category) {
        if (repository.existsById(id)) {
            category.setId(id);
            repository.save(category);
            return ResponseEntity.ok("Category updated.");
        } else {
            return ResponseEntity.status(404).body("Category not found.");
        }
    }

    // Deletar um usuário por id =============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteCategoryIdService(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Category created.");
        } else {
            return ResponseEntity.status(404).body("Category not found.");
        }
    }
}