package com.projetofinal.projetofinal.dtos.Category;

import com.projetofinal.projetofinal.model.Category.Category;

public class CategoryResponseDto {
    private Integer id;
    private String name;

    // Construtores ============================================================

    // Construtor no args
    public CategoryResponseDto() {
    }

    // Construtor all args
    public CategoryResponseDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Construtor sem o id
    public CategoryResponseDto(String name) {
        this.name = name;
    }

    // Construtor usando Category como args
    public CategoryResponseDto(Category category) {
        id = category.getId();
        name = category.getName();
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
