package com.projetofinal.projetofinal.dtos.Transaction;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.Transaction.Transaction;

public class TransactionResponseDto {
    private Integer id;
    private Double amount;
    private Integer type;
    private LocalDate date;
    private String description;
    private String method;
    private String bankName;
    private String categoryName;
    private Integer bankAccountId; // ID da conta bancária
    private Integer categoryId; // ID da categoria

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
        this.method = transaction.getMethod();
        this.bankName = transaction.getBankName();
        this.categoryName = transaction.getCategoryName();
    }

    // Getters e Setters
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
