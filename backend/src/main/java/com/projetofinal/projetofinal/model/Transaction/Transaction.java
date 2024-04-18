package com.projetofinal.projetofinal.model.Transaction;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.Category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    private Integer type;

    @NotNull(message = "Date is mandatory")
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDate date;

    private Double balanceAfterTransaction;

    // Relações entre tabelas

    @ManyToOne
    @JoinColumn(name = "bankaccount_id")
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Construtores ============================================================

    // Construtor no args
    public Transaction() {
    }

    // Construtor all args
    public Transaction(Double amount, LocalDate date, Integer type) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Getters e Setters =======================================================

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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    // Métodos de relação entre tabelas ===================================

}
