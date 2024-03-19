package com.projetofinal.projetofinal.dtos;

import com.projetofinal.projetofinal.model.Transaction;

public class TransactionDto {
    private Integer id;
    private Double amount;
    private Integer categoryId;
    private Integer bankAccountId;

    // Construtores =============================================================

    // Construtor no args
    public TransactionDto() {
    }

    // Construtor all args
    public TransactionDto(Integer id, Double amount, Integer categoryId) {
        this.id = id;
        this.amount = amount;
        this.categoryId = categoryId;
    }

    // Construtor sem o id e com o id bankaccount
    public TransactionDto(Double amount, Integer categoryId, Integer bankAccountId) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.bankAccountId = bankAccountId;
    }

    // Construtor usando Transacoes como args
    public TransactionDto(Transaction transaction) {
        id = transaction.getId();
        amount = transaction.getAmount();
        categoryId = transaction.getCategoryId();
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

    public Integer getCategoryIdDto() {
        return categoryId;
    }

    public void setCategoryIdDto(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

}
