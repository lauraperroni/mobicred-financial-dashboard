package com.projetofinal.projetofinal.model.Category;

import com.projetofinal.projetofinal.model.Transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

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
