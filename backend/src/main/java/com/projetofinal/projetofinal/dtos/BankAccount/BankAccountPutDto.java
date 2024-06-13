package com.projetofinal.projetofinal.dtos.BankAccount;

public record BankAccountPutDto (
    String accountType, 
    Double balance,
    String bankName,
    Double billingBalance,
    Integer bankNumber,
    String nextBillingDate
) {
    
}
