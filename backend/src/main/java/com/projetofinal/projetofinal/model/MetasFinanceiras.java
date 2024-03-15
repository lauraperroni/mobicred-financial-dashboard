package com.projetofinal.projetofinal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MetasFinanceiras {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idUsuario;
    private String descricao;
    private Double valor;
    private Date data;

    // Construtores ============================================================

    // Construtor no args
    public MetasFinanceiras() {
    }

    // Construtor all args
    public MetasFinanceiras(Integer id, Integer idUsuario, String descricao, Double valor, Date data) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
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

    public String getdescricao() {
        return descricao;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
