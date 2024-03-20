package com.projetofinal.projetofinal.dtos.User;

import com.projetofinal.projetofinal.model.User;

public class UserDto {
    private Integer id;
    private String name;
    private String email;

    // Construtores =============================================================
    public UserDto() {
    }

    public UserDto(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDto(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }

    // Getters e Setters =======================================================
    public Integer getIdDto() {
        return id;
    }

    public void setIdDto(Integer id) {
        this.id = id;
    }

    public String getNameDto() {
        return name;
    }

    public void setNameDto(String name) {
        this.name = name;
    }

    public String getEmailDto() {
        return email;
    }

    public void setEmailDto(String email) {
        this.email = email;
    }

}
