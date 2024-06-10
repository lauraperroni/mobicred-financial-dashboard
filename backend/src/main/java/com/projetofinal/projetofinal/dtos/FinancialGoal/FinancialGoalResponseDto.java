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
    private Integer type;
    private Double saved;

    // Construtores =============================================================

    // Construtor all args
    public FinancialGoalResponseDto(Integer id, String description, Double amount, LocalDate creationDate,
            LocalDate deadline, Double saved, String name, Integer type) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.saved = saved;
        this.name = name;
        this.type = type;
        
    }

    // Construtor sem id
    public FinancialGoalResponseDto(String description, Double amount, LocalDate creationDate, LocalDate deadline,
            String name, Integer type, Double saved) {
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.name = name;
        this.type = type;
        this.saved = saved;
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
        type = goals.getType();
        saved = goals.getSaved();
    }

    // Getters e Setters ========================================================

    public Double getSaved() {
        return saved;
    }

    public void setSaved(Double saved) {
        this.saved = saved;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    // Construtor no args
    public FinancialGoalResponseDto() {
    }

    public FinancialGoalResponseDto(Double amount2, LocalDate creationDate2, String description2, Integer id2,
            LocalDate deadline2, Double saved2, String name2, Integer type2) {
    }

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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
        throw new UnsupportedOperationException("Unimplemented method 'fromEntities'");
    }

}
