package com.projetofinal.projetofinal.dtos.BankAccount;

import com.projetofinal.projetofinal.model.BankAccount.BankAccount;

public class BankAccountResponseDto {
    private Integer id;
    private String accountType;
    private Double balance;
    private String bankName;
    private Double billingBalance;
    private String nextBillingDate;
    private Integer bankNumber; // Adicionando bankNumber

    // Construtores ============================================================

    public String getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(String nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    public Double getBillingBalance() {
        return billingBalance;
    }

    public void setBillingBalance(Double billingBalance) {
        this.billingBalance = billingBalance;
    }

    // Construtor no args
    public BankAccountResponseDto() {
    }

    // Construtor usando BankAccount como args
    public BankAccountResponseDto(BankAccount contas) {
        id = contas.getId();
        accountType = contas.getAccountType();
        balance = contas.getBalance();
        bankName = contas.getBankName();
        billingBalance = contas.getBillingBalance();
        nextBillingDate = contas.getNextBillingDate();
        bankNumber = contas.getBankNumber(); // Adicionando ao construtor
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(Integer bankNumber) {
        this.bankNumber = bankNumber;
    }
}
