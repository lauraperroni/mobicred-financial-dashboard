package com.projetofinal.projetofinal.dtos.User;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.model.User.UserRole;

public class UserResponseDto {
    private Integer id;
    private UserRole role;
    private String cpf;
    private String name;
    private String email;
    @SuppressWarnings("unused")
    private String password;
    private String street;
    private Integer number;
    private String district;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
    private LocalDate registerDate;

    // Construtores =============================================================
    public UserResponseDto() {
    }

    public UserResponseDto(Integer id, UserRole role, String cpf, String name, String email, String password, String street, Integer number,
            String district, String complement, String city, String state, String zipCode, LocalDate registerDate) {
        this.id = id;
        this.role = role;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.street = street;
        this.number = number;
        this.district = district;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.registerDate = registerDate;
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.cpf = user.getCpf();
        this.name = user.getName();
        this.email = user.getEmail();
        this.street = user.getStreet();
        this.number = user.getNumber();
        this.district = user.getDistrict();
        this.complement = user.getComplement();
        this.city = user.getCity();
        this.state = user.getState();
        this.zipCode = user.getZipCode();
        this.registerDate = user.getRegisterDate();
        // Se a lista de contas bancárias e metas financeiras também precisar ser mapeada,
        // faça isso aqui.
    }

    // Getters e Setters =======================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
