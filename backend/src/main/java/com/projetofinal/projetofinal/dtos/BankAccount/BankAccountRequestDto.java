package com.projetofinal.projetofinal.dtos.BankAccount;

public record BankAccountRequestDto(
        String accountType,
        Double balance,
        Integer userId) {

}
