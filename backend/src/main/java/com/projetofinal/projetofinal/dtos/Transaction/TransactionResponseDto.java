package com.projetofinal.projetofinal.dtos.Transaction;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.Transaction.Transaction;

public class TransactionResponseDto {
    private Integer id;
    private Double amount;
    private Integer type;
    private LocalDate date;
    

    // Constructors =============================================================

    // No-args constructor
    public TransactionResponseDto() {
    }

    // Constructor using Transaction as an argument
    public TransactionResponseDto(Transaction transaction) {
        this.id = transaction.getId(); // Assuming the Transaction class has a getId() method
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.date = transaction.getDate();
    }

    // Getters and Setters ======================================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}