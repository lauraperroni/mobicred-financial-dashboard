package com.projetofinal.projetofinal.dtos.Transaction;

import com.projetofinal.projetofinal.model.Transaction.Transaction;

public class TransactionDto {
    private Integer id;
    private Double amount;
    private Integer bankAccountId;

    // Construtores =============================================================

    // Construtor no args
    public TransactionDto() {
    }

    // Construtor all args
    public TransactionDto(Integer id, Double amount, Integer categoryId) {
        this.id = id;
        this.amount = amount;
    }

    // Construtor sem o id e com o id bankaccount
    public TransactionDto(Double amount, Integer categoryId, Integer bankAccountId) {
        this.amount = amount;
        this.bankAccountId = bankAccountId;
    }

    // Construtor usando Transacoes como args
    public TransactionDto(Transaction transaction) {
        id = transaction.getId();
        amount = transaction.getAmount();
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

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

}
