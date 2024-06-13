package com.projetofinal.projetofinal.dtos.User;

public record UserPutDto(
        String cpf,
        String name,
        String email,
        String street,
        Integer number,
        String district,
        String complement,
        String city,
        String state,
        String zipCode) {

}
