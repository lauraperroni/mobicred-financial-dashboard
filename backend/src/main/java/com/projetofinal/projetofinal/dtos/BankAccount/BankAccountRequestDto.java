package com.projetofinal.projetofinal.dtos.BankAccount;

public record BankAccountRequestDto(
                String name,
                String accountType,
                Double balance,
                Integer userId) {

}
