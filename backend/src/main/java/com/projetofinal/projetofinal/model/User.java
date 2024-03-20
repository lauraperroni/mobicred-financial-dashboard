package com.projetofinal.projetofinal.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends RepresentationModel<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpf;
    private String name;
    private String email;
    private String password;

    // Listas de relacionamento entre tabelas

    // User - BankAcconut
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    // User - FinancialGoal
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FinancialGoal> financialGoals;

    // Construtores ============================================================

    // Construtor sem argumentos
    public User() {
    }

    // Construtor all args
    public User(Integer id, String cpf, String name, String email, String password) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Construtor some args
    public User(String cpf, String name, String email, String password) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters e Setters =======================================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método de relação entre tabelas ================================

    // User - BankAccount 1-*
    public void addBankAccountToList(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        bankAccount.setUser(this);
    }

    // User - FinancialGoal 1-*
    public void addFinancialGoalToList(FinancialGoal financialGoal) {
        financialGoals.add(financialGoal);
        financialGoal.setUser(this);
    }
}
