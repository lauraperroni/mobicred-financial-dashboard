package com.projetofinal.projetofinal.dtos.FinancialGoal;

import java.time.LocalDate;

public record FinancialGoalPutDto(
    String name,
    Double amount,
    String description,
    Double saved,
    LocalDate deadline,
    Integer type
){}
