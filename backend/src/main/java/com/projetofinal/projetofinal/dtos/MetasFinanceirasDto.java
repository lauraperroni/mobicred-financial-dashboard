package com.projetofinal.projetofinal.dtos;

import java.sql.Date;

import com.projetofinal.projetofinal.model.MetasFinanceiras;

public class MetasFinanceirasDto {
    private String descricao;
    private Double valor;
    private Date data;

    // Construtores =============================================================

    // Construtor no args
    public MetasFinanceirasDto() {
    }

    // Construtor all args
    public MetasFinanceirasDto(String descricao, Double valor, Date data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Construtor usando model
    public MetasFinanceirasDto(MetasFinanceiras meta) {
        descricao = meta.getdescricao();
        valor = meta.getValor();
        data = meta.getData();
    }

    // Getters e Setters ========================================================

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
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
