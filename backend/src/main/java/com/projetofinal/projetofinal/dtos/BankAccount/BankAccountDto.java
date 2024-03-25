package com.projetofinal.projetofinal.dtos.BankAccount;

import com.projetofinal.projetofinal.model.BankAccount.BankAccount;

public class BankAccountDto {
    private Integer id;
    private String accountType;
    private Double balance;
    private String name;

    // Construtores ============================================================

    // Construtor no args
    public BankAccountDto() {
    }

    // Construtor usando BankAccount como args
    public BankAccountDto(BankAccount contas) {
        id = contas.getId();
        accountType = contas.getAccountType();
        balance = contas.getBalance();
        name = contas.getName();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
