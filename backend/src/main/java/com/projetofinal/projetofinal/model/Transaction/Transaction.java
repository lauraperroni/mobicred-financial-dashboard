package com.projetofinal.projetofinal.model.Transaction;

import java.time.LocalDate;
import java.util.Collection;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionPutDto;
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
    private String method;

    private String description;
    private Integer type;
    private LocalDate date;

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
    public Transaction(Double amount, LocalDate date, Integer type, String method, String description) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.description = description;
        this.method = method;
    }

    public void putData(TransactionPutDto trans, BankAccount bankAccount, Category category){
        this.amount = trans.amount();
        this.date = trans.date();
        this.type = trans.type();
        this.method = trans.method();
        this.description = trans.description();
        this.bankAccount = bankAccount;
        this.category = category;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Transaction> getTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransactions'");
    }

    public String getBankName() {
        return bankAccount.getBankName();
    }

    public String getCategoryName() {
        return category.getName();
    }

    public Integer getBankAccountId() {
        Integer bankAccountId = bankAccount.getId();
        return bankAccountId;
    }

    public Integer getCategoryId() {
        Integer categoryId = category.getId();
        return categoryId;
    }


    // Métodos de relação entre tabelas ===================================

}
