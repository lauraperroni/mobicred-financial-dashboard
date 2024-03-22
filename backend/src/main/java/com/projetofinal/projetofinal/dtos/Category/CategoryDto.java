package com.projetofinal.projetofinal.dtos.Category;

import com.projetofinal.projetofinal.model.Category.Category;

public class CategoryDto {
    private Integer id;
    private String name;

    // Construtores ============================================================

    // Construtor no args
    public CategoryDto() {
    }

    // Construtor all args
    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Construtor sem o id
    public CategoryDto(String name) {
        this.name = name;
    }

    // Construtor usando Category como args
    public CategoryDto(Category category) {
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
