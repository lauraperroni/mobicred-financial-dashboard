package com.projetofinal.projetofinal.dtos;

import com.projetofinal.projetofinal.model.BankAccount;

public class BankAccountDto {
    private Integer id;
    private String accountType;
    private Double balance;
    private Integer userId;
    // Construtores ============================================================

    // Construtor no args
    public BankAccountDto() {
    }

    // Construtor all args
    public BankAccountDto(Integer id, String accountType, Double balance, Integer userId) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
    }

    // Construtor sem o id
    public BankAccountDto(String accountType, Double balance, Integer userId) {
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
    }

    // Construtor usando BankAccount como args
    public BankAccountDto(BankAccount contas) {
        id = contas.getId();
        accountType = contas.getAccountType();
        balance = contas.getBalance();
    }

    // Getters e Setters =======================================================

    public Integer getIdDto() {
        return id;
    }

    public void setIdDto(Integer id) {
        this.id = id;
    }

    public String getAccountTypeDto() {
        return accountType;
    }

    public void setAccountTypeDto(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalanceDto() {
        return balance;
    }

    public void setBalanceDto(Double balance) {
        this.balance = balance;
    }

    public Integer getUserIdDto() {
        return userId;
    }

    public void setUserIdDto(Integer userId) {
        this.userId = userId;
    }

}
