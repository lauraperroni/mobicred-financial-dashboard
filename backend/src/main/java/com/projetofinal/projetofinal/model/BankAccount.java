package com.projetofinal.projetofinal.model;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Lista de trançaões dessa conta bancária
    @OneToMany(mappedBy = "bankaccount", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // Construtores ============================================================

    // Construtor no args
    public BankAccount() {
    }

    // Construtor all args
    public BankAccount(String accountType, Double balance) {
        this.accountType = accountType;
        this.balance = balance;
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

    // Métodos de relação entre tabelas ===================================

    // BankAccount - Transactions 1 - *
    public void addBankAccountToList(Transaction transaction) {
        transactions.add(transaction);
        transaction.setBankAccount(this);
    }
}
