package com.projetofinal.projetofinal.dtos.Transaction;

import java.sql.Date;

public record TransactionRequestDto(
        Double amount,
        Date date,
        Integer categoryId,
        String categoryName,
        Integer bankAccountId) {
}
