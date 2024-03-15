package com.projetofinal.projetofinal.dtos;

import com.projetofinal.projetofinal.model.ContasBancarias;

public class ContasBancariasDto {
    private Integer id;
    private String tipoConta;
    private Double saldo;

    // Construtores ============================================================

    // Construtor no args
    public ContasBancariasDto() {
    }

    // Construtor all args
    public ContasBancariasDto(Integer id, String tipoConta, Double saldo) {
        this.id = id;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    // Construtor usando ContasBancarias como args
    public ContasBancariasDto(ContasBancarias contas) {
        id = contas.getId();
        tipoConta = contas.getTipoConta();
        saldo = contas.getSaldo();
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
