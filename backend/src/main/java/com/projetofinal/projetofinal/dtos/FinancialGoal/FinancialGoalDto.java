package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.sql.Date;

import com.projetofinal.projetofinal.model.FinancialGoal;

public class FinancialGoalDto {
    private Integer id;
    private String description;
    private Double amount;
    private Date date;

    // Construtores =============================================================

    // Construtor no args
    public FinancialGoalDto() {
    }

    // Construtor all args
    public FinancialGoalDto(Integer id, String description, Double amount, Date date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Construtor sem id
    public FinancialGoalDto(String description, Double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Construtor usando model
    public FinancialGoalDto(FinancialGoal goal) {
        id = goal.getId();
        description = goal.getDescription();
        amount = goal.getAmount();
        date = goal.getDate();
    }

    // Getters e Setters ========================================================

    public String getDescriptionDto() {
        return description;
    }

    public void setDescriptionDto(String description) {
        this.description = description;
    }

    public Double getAmountDto() {
        return amount;
    }

    public void setAmountDto(Double amount) {
        this.amount = amount;
    }

    public Date getDateDto() {
        return date;
    }

    public void setDateDto(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
