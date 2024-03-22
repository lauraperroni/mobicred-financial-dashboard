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

import com.projetofinal.projetofinal.dtos.Category.CategoryRequestDto;
import com.projetofinal.projetofinal.model.Category.Category;
import com.projetofinal.projetofinal.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class ControllerCategory {

    @Autowired
    private CategoryService service;

    // Endpoints
    // =============================================================================

    // Trazer todas as transacoes ==================================================
    @GetMapping("/all")
    public List<Category> getAllTransactionDto() {
        return service.getAllCategoryService();
    }

    // Traz uma transacao pelo id =================================================
    @GetMapping("/{id}")
    public Category getCAtegoryId(@PathVariable Integer id) {
        return service.getCategoryIdService(id);
    }

    // Adicionar novo usuário ======================================================
    @PostMapping("/new")
    public ResponseEntity<String> postNewCategory(@RequestBody Category category) {
        service.postNewCategoryService(category);
        return ResponseEntity.ok("New category registered.");
    }

    // Update de um usuário por id ================================================
    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateTransaction(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        return service.putUpdateCategoryService(id, category);
    }

    // Deletar um usuário por id ==================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryId(@PathVariable Integer id) {
        return service.deleteCategoryIdService(id);
    }
}
