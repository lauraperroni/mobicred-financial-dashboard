package com.projetofinal.projetofinal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes")
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idcontabancaria;
    private Double valor;
    private Date data;
    private Integer idcategoria;

    // Construtores
    public Transacoes() {
    }

    public Transacoes(Integer idcontabancaria, Double valor, Date data, Integer idcategoria) {
        this.valor = valor;
        this.data = data;
        this.idcategoria = idcategoria;
        this.idcontabancaria = idcontabancaria;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getidcontabancaria() {
        return idcontabancaria;
    }

    public void setidcontabancaria(Integer idcontabancaria) {
        this.idcontabancaria = idcontabancaria;
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

    public Integer getidcategoria() {
        return idcategoria;
    }

    public void setidcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }
}
