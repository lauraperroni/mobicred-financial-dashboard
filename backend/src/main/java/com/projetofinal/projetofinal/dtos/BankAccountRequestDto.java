package com.projetofinal.projetofinal.dtos;

public record BankAccountRequestDto(
        String accountType,
        Double balance,
        Integer userId) {

}
