package com.projetofinal.projetofinal.model.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;

    // Construtores ============================================================

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Construtor no args
    public Category() {
    }

    // Construtor all args
    public Category(String name) {
        this.name = name;
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

    public Integer categoryId() {
        throw new UnsupportedOperationException("Unimplemented method 'categoryId'");
    }

    // Métodos de relação entre tabelas ===================================

}
