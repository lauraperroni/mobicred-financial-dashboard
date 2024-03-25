package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.projetofinal.projetofinal.dtos.Category.CategoryResponseDto;
import com.projetofinal.projetofinal.model.Category.Category;
import com.projetofinal.projetofinal.repository.Category.CategoryRepository;

@Service
public class CategoryService {

    // Repositório que vai conversar com o banco de dados
    @Autowired
    private CategoryRepository categoryRepository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as categorias ===============================================
    public List<Category> getAllCategoryService() {
        return categoryRepository.findAll();
    }

    // Trazer todas as categorias Dto ===========================================
    public List<CategoryResponseDto> getAllCategoryDtoService() {
        List<Category> categories = categoryRepository.findAll();
        return mapCategoryListToCategoryDtoList(categories);
    }

    private List<CategoryResponseDto> mapCategoryListToCategoryDtoList(List<Category> categories) {
        return categories.stream()
                .map(this::mapCategoryToCategoryDtoService)
                .collect(Collectors.toList());
    }

    // Converte uma accountBancaria em uma accountBancariaDto ===============
    private CategoryResponseDto mapCategoryToCategoryDtoService(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto(category);
        return dto;
    }

    // Traz uma categoria pelo id ================================================
    @SuppressWarnings("null")
    public Category getCategoryIdService(Integer id) {
        return categoryRepository.findById(id).get();
    }

    // Adicionar novo usuário =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewCategoryService(Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok("New category created.");
    }

    // Update de um usuário por id ============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateCategoryService(Integer id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            categoryRepository.save(category);
            return ResponseEntity.ok("Category updated.");
        } else {
            return ResponseEntity.status(404).body("Category not found.");
        }
    }

    // Deletar um usuário por id =============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteCategoryIdService(@PathVariable Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Category created.");
        } else {
            return ResponseEntity.status(404).body("Category not found.");
        }
    }
}