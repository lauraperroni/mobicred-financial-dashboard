package com.projetofinal.projetofinal.model.Transaction;

import java.sql.Date;
import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.Category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private Date date;

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
    public Transaction(Double amount, Date date) {
        this.amount = amount;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    // Métodos de relação entre tabelas ===================================

}
