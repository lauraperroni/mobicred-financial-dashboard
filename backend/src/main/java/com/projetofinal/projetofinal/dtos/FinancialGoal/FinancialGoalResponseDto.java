package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.time.LocalDate;
import com.projetofinal.projetofinal.model.FinancialGoal.FinancialGoal;

public class FinancialGoalResponseDto {
    private Integer id;
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
            LocalDate deadline) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.deadline = deadline;
    }

    // Construtor sem id
    public FinancialGoalResponseDto(String description, Double amount, LocalDate creationDate, LocalDate deadline) {
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.deadline = deadline;
    }

    // Construtor usando model
    public FinancialGoalResponseDto(FinancialGoal goals) {
        id = goals.getId();
        description = goals.getDescription();
        amount = goals.getAmount();
        creationDate = goals.getCreationDate();
        creationDate = goals.getCreationDate();
        deadline = goals.getDeadline();
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

}
