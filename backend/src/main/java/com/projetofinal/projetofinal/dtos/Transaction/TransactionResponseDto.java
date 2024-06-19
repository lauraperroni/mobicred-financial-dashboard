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
    private String bankName; // Adicionando bankName
    private String categoryName; // Adicionando categoryName

    // Constructors =============================================================
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

    // No-args constructor
    public TransactionResponseDto() {
    }

    // Constructor using Transaction as an argument
    public TransactionResponseDto(Transaction transaction) {
        this.id = transaction.getId(); // Assuming the Transaction class has a getId() method
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.date = transaction.getDate();
        this.method = transaction.getMethod();
        this.description = transaction.getDescription();
        this.bankName = transaction.getBankName(); // Assuming Transaction class has a getBankName() method
        this.categoryName = transaction.getCategoryName(); // Assuming Transaction class has a getCategoryName() method
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
