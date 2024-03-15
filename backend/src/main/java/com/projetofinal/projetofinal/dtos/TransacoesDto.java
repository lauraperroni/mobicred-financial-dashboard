package com.projetofinal.projetofinal.dtos;

import com.projetofinal.projetofinal.model.Transacoes;

public class TransacoesDto {
    private Integer id;
    private Double valor;
    private Integer idcategoria;
    // Construtores =============================================================

    // Construtor no args
    public TransacoesDto() {
    }

    // Construtor all args
    public TransacoesDto(Integer id, Double valor, Integer idcategoria) {
        this.id = id;
        this.valor = valor;
        this.idcategoria = idcategoria;
    }

    // Construtor usando Transacoes como args
    public TransacoesDto(Transacoes transacao) {
        id = transacao.getId();
        valor = transacao.getValor();
        idcategoria = transacao.getidcategoria();
    }

    // Getters e Setters ========================================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getidcategoria() {
        return idcategoria;
    }

    public void setidcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

}
