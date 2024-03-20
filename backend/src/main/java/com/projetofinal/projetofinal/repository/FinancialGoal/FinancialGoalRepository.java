package com.projetofinal.projetofinal.repository.FinancialGoal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.FinancialGoal;

@Repository
public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Integer> {

}
