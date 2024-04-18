package com.projetofinal.projetofinal.dtos.User;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.User.User;

public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private LocalDate registerDate;

    // Construtores =============================================================
    public UserResponseDto() {
    }

    public UserResponseDto(Integer id, String name, String email, LocalDate registerDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
    }

    public UserResponseDto(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        registerDate = user.getRegisterDate();
    }

    // Getters e Setters =======================================================
    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return name;
    }

    public void seUsertName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

}
