package com.projetofinal.projetofinal.dtos.User;

public class RegisterDTO {
    private String email;
    private String password;
    private String cpf;
    private String name;
    private Integer number;
    private String zipCode;
    private String street;
    private String district;
    private String state;
    private String city;
    private String complement;

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String password, String cpf, String name, Integer number, String zipCode,
            String street, String district, String state, String city, String complement) {
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.name = name;
        this.number = number;
        this.zipCode = zipCode;
        this.street = street;
        this.district = district;
        this.state = state;
        this.city = city;
        this.complement = complement;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
