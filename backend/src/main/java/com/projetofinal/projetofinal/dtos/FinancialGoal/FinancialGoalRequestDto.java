package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.time.LocalDate;

public record FinancialGoalRequestDto(
        Double amount,
        LocalDate creationDate,
        String description,
        Integer userid,
        LocalDate deadline) {

}
