package com.projetofinal.projetofinal.dtos.User;

public class Register2DTO {
    private String email;
    private String password;

    public Register2DTO() {
    }

    public Register2DTO(String email, String password) {
        this.email = email;
        this.password = password;

    }

    // Getters e Setters omitidos para brevidade
    // Certifique-se de incluir getters e setters para todos os campos adicionais

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
}
