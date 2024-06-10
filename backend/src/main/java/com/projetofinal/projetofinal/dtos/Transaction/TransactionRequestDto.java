package com.projetofinal.projetofinal.dtos.Transaction;

import java.time.LocalDate;

public record TransactionRequestDto(
                Double amount,
                LocalDate date,
                Integer type,
                Integer categoryId,
                String categoryName,
                Integer bankAccountId,
                String method,
                String description) {

}
