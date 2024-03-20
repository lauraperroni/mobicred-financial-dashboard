package com.projetofinal.projetofinal.model;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FinancialGoals")
public class FinancialGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private Double amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Construtores ============================================================

    // Construtor no args
    public FinancialGoal() {
    }

    public FinancialGoal(String description, Double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Métodos de relacionamento entre tabelas

}
