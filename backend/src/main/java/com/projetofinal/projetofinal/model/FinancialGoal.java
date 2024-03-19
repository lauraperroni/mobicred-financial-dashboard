package com.projetofinal.projetofinal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FinancialGoals")
public class FinancialGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String description;
    private Double amount;
    private Date date;

    // Construtores ============================================================

    // Construtor no args
    public FinancialGoal() {
    }

    // Construtor all args
    public FinancialGoal(Integer id, Integer userId, String description, Double amount, Date date) {
        this.id = id;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

}
