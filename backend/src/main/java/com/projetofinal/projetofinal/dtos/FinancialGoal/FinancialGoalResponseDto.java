package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.time.LocalDate;
import java.util.List;

import com.projetofinal.projetofinal.model.FinancialGoal.FinancialGoal;

public class FinancialGoalResponseDto {
    private Integer id;
    private String name;
    private String description;
    private Double amount;
    private LocalDate creationDate;
    private LocalDate deadline;

    // Construtores =============================================================

    // Construtor no args
    public FinancialGoalResponseDto() {
    }

    // Construtor all args
    public FinancialGoalResponseDto(Integer id, String description, Double amount, LocalDate creationDate,
            LocalDate deadline, String name) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.name = name;
    }

    // Construtor sem id
    public FinancialGoalResponseDto(String description, Double amount, LocalDate creationDate, LocalDate deadline,
            String name) {
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.name = name;
    }

    // Construtor usando model
    public FinancialGoalResponseDto(FinancialGoal goals) {
        id = goals.getId();
        description = goals.getDescription();
        amount = goals.getAmount();
        creationDate = goals.getCreationDate();
        creationDate = goals.getCreationDate();
        deadline = goals.getDeadline();
        name = goals.getName();
    }

    // Getters e Setters ========================================================

    public Integer getIdDto() {
        return id;
    }

    public void setIdDto(Integer id) {
        this.id = id;
    }

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

    public LocalDate getCreationDateDto() {
        return creationDate;
    }

    public void setCreationDateDto(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<FinancialGoalResponseDto> fromEntities(List<FinancialGoal> goals) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromEntities'");
    }

}
