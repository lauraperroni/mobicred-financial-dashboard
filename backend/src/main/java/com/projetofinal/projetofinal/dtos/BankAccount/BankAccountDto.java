package com.projetofinal.projetofinal.dtos.BankAccount;

import com.projetofinal.projetofinal.model.BankAccount;

public class BankAccountDto {
    private Integer id;
    private String accountType;
    private Double balance;

    // Construtores ============================================================

    // Construtor no args
    public BankAccountDto() {
    }

    // Construtor all args
    public BankAccountDto(Integer id, String accountType, Double balance) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;

    }

    // Construtor sem o id
    public BankAccountDto(String accountType, Double balance) {
        this.accountType = accountType;
        this.balance = balance;
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

}
