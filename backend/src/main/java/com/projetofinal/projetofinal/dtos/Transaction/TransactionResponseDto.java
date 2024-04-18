package com.projetofinal.projetofinal.dtos.Transaction;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.Transaction.Transaction;

public class TransactionResponseDto {
    private Integer id;
    private Double amount;
    private Integer type;
    private LocalDate date;
    private String bankName;
    private String categoryName;

    // Construtores =============================================================

    // Construtor no args
    public TransactionResponseDto() {
    }

    // Construtor usando Transacoes como args
    public TransactionResponseDto(Transaction transaction) {
        id = transaction.getId();
        amount = transaction.getAmount();
        type = transaction.getType();
        date = transaction.getDate();
        bankName = transaction.getBankAccount().getBankName();
        categoryName = transaction.getCategory().getName();
    }

    // Getters e Setters ========================================================
    public Integer getIdDto() {
        return id;
    }

    public void setIdDto(Integer id) {
        this.id = id;
    }

    public Double getAmountDto() {
        return amount;
    }

    public void setAmountDto(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
