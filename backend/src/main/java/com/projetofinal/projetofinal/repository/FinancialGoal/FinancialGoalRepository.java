package com.projetofinal.projetofinal.repository.FinancialGoal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.FinancialGoal.FinancialGoal;

@Repository
public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Integer> {

    List<FinancialGoal> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);

    List<FinancialGoal> findByDeadlineBetweenOrderByDeadlineAsc(LocalDate startDate, LocalDate endDate);

    List<FinancialGoal> findByDeadlineBetweenOrderByDeadlineDesc(LocalDate startDate, LocalDate endDate);

    List<FinancialGoal> findByDeadline(LocalDate deadline);

    List<FinancialGoal> findByName(String name);

    List<FinancialGoal> findByType(int type);

}
