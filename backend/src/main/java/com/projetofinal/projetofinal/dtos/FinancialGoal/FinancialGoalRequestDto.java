package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.sql.Date;

public record FinancialGoalRequestDto(
        String description,
        Double amount,
        Date date,
        Integer userId) {

}
