package com.projetofinal.projetofinal.dtos.BankAccount;

public record BankAccountRequestDto(
        String bankName,
        Integer bankNumber,
        String accountType,
        Double balance,
        String nextBillingDate,
        Double billingBalance) {
}
