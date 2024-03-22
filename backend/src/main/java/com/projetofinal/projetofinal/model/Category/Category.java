package com.projetofinal.projetofinal.model.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    // Construtores ============================================================

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
