package com.projetofinal.projetofinal.model.FinancialGoal;

import java.time.LocalDate;

import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalRequestDto;
import com.projetofinal.projetofinal.model.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;

@Entity
@Table(name = "FinancialGoals")
public class FinancialGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double amount;
    private LocalDate creationDate;
    private Integer type;
    private Double saved;

    @Future
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Construtores ============================================================

    // Construtor no args
    public FinancialGoal() {
    }

    public FinancialGoal(String description, Double amount, LocalDate date, LocalDate deadline, String name,
            Integer type, Double saved) {
        this.description = description;
        this.amount = amount;
        this.creationDate = LocalDate.now();
        this.deadline = deadline;
        this.name = name;
        this.type = type;
        this.saved = saved;
    }

    public FinancialGoal(FinancialGoalRequestDto goal) {
        this.name = goal.getName();
        this.description = goal.getDescription();
        this.amount = goal.getAmount();
        this.creationDate = LocalDate.now(); // Supondo que a data de criação deva ser a data atual
        this.type = goal.getType();
        this.saved = 0.0; // Inicializa o valor salvo como 0.0
        this.deadline = goal.getDeadline();
    }

    // Getters e Setters =======================================================

    public Double getSaved() {
        return saved;
    }

    public void setSaved(Double saved) {
        this.saved = saved;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    // Métodos de relacionamento entre tabelas

}
