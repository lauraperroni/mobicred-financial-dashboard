package com.projetofinal.projetofinal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contasBancarias")
public class ContasBancarias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idUsuario;
    private String tipoConta;
    private Double saldo;

    // Construtores ============================================================

    // Construtor no args
    public Integer getId() {
        return id;
    }

    // Construtor all args
    public ContasBancarias(Integer id, Integer idUsuario, String tipoConta, Double saldo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    // Getters e Setters =======================================================
    public ContasBancarias() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
