package com.projetofinal.projetofinal.model.BankAccount;

import java.util.List;

import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountRequestDto;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.model.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "BankAccounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountType;
    private Double balance;
    private String bankName;
    private Double billingBalance;
    public Double getBillingBalance() {
        return billingBalance;
    }

    public void setBillingBalance(Double billingBalance) {
        this.billingBalance = billingBalance;
    }

    private String nextBillingDate;

    public String getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(String nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Lista de trançaões dessa conta bancária
    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // Construtores ============================================================

    // Construtor no args
    public BankAccount() {
    }

    // Construtor all args
    public BankAccount(String accountType, Double balance, String bankName, String nextBillingDate, Double billingBalance, User user) {
        this.accountType = accountType;
        this.balance = balance;
        this.bankName = bankName;
        this.nextBillingDate = nextBillingDate;
        this.billingBalance = billingBalance;
        this.user = user;
    }

    public BankAccount(BankAccountRequestDto dto){
        this.accountType = dto.accountType();
        this.balance = dto.balance();
        this.bankName = dto.bankName();
        this.nextBillingDate = dto.nextBillingDate();
        this.billingBalance = dto.billingBalance();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    // Métodos de relação entre tabelas ===================================

    // BankAccount - Transactions 1 - *
    public void addTransactionToList(Transaction transaction) {
        transactions.add(transaction);
        transaction.setBankAccount(this);
    }

    // Dentro do método deposit da classe BankAccount
    public void deposit(Double amount, Transaction transaction) {
        this.balance += amount;
        transaction.setBalanceAfterTransaction(this.balance);
    }

    public void withdraw(Double amount, Transaction transaction) {
        this.balance -= amount;
        transaction.setBalanceAfterTransaction(this.balance);
    }
}
