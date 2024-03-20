package com.projetofinal.projetofinal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idBankAccount;
    private Double amount;
    private Date date;
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "bankaccount_id")
    private BankAccount bankAccount;

    // Construtores ============================================================

    // Construtor no args
    public Transaction() {
    }

    // Construtor all args
    public Transaction(Integer idBankAccount, Double amount, Date date, Integer categoryId) {
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.idBankAccount = idBankAccount;
    }

    // Construtor some args
    public Transaction(Double amount, Date date, Integer categoryId) {
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(Integer idBankAccount) {
        this.idBankAccount = idBankAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;

    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Métodos de relação entre tabelas ===================================

}
