package com.projetofinal.projetofinal.dtos;

import com.projetofinal.projetofinal.model.Usuarios;

public class UsuariosDto {
    private Integer id;
    private String nome;
    private String email;

    // Construtores =============================================================
    public UsuariosDto() {
    }

    public UsuariosDto(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UsuariosDto(Usuarios usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
    }

    // Getters e Setters =======================================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
