package com.projetofinal.projetofinal.dtos.BankAccount;

public record BankAccountRequestDto(
        String bankName,
        String accountType,
        Double balance,
        Integer userId) {

}
