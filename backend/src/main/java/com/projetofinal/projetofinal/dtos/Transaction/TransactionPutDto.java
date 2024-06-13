package com.projetofinal.projetofinal.dtos.Transaction;

import java.time.LocalDate;

public record TransactionPutDto(
        Double amount,
        LocalDate date,
        Integer type,
        Integer bankAccountId,
        Integer categoryId,
        String method,
        String description

) {}
