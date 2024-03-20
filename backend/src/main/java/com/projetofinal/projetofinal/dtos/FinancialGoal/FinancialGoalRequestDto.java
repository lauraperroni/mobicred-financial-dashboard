package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.sql.Date;

public record FinancialGoalRequestDto(
                Double amount,
                Date date,
                String description,
                Integer userid) {

}
