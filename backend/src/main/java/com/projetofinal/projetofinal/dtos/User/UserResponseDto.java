package com.projetofinal.projetofinal.dtos.User;

import java.time.LocalDate;

import com.projetofinal.projetofinal.model.User.User;

public class UserResponseDto {
    private Integer id;
    private String cpf;
    private String name;
    private String email;
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

    public UserResponseDto(Integer id, String cpf, String name, String email, String password, String street, Integer number,
            String district, String complement, String city, String state, String zipCode, LocalDate registerDate) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.street = street;
        this.number = number;
        this.district = district;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.registerDate = registerDate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
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
