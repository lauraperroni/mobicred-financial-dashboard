package com.projetofinal.projetofinal.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalResponseDto;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalRequestDto;
import com.projetofinal.projetofinal.model.FinancialGoal.FinancialGoal;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.repository.FinancialGoal.FinancialGoalRepository;
import com.projetofinal.projetofinal.repository.User.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FinancialGoalService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private FinancialGoalRepository financialGoalRepository;

    @Autowired
    private UserRepository userRepository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as metas financeiras ========================================
    public List<FinancialGoal> getAllFinancialGoalService() {
        return financialGoalRepository.findAll();
    }

    // Trazer todas as metas financeiras Dto ====================================
    public List<FinancialGoalResponseDto> getAllFinancialGoalDtoService() {
        List<FinancialGoal> goals = financialGoalRepository.findAll();
        return mapGoalListToGoalDtoListService(goals);
    }

    // Pega os objetos transformados em DTO e cria uma lista
    public List<FinancialGoalResponseDto> mapGoalListToGoalDtoListService(List<FinancialGoal> goals) {
        return goals.stream()
                .map(this::mapGoalToGoalDtoService)
                .collect(Collectors.toList());
    }

    // Transforma os objetos model em DTO
    public FinancialGoalResponseDto mapGoalToGoalDtoService(FinancialGoal goal) {
        FinancialGoalResponseDto dto = new FinancialGoalResponseDto(goal);
        return dto;
    }

    // Trazer uma meta financeira pelo id ====================================
    @SuppressWarnings("null")
    public FinancialGoal getFinancialGoalIdService(Integer id) {
        return financialGoalRepository.findById(id).get();
    }

    // Trazer metas financeiras pelo id Dto ==================================
    public FinancialGoalResponseDto getFinancialGoalIdDtoService(Integer id) {
        @SuppressWarnings("null")
        FinancialGoal meta = financialGoalRepository.findById(id).get();
        FinancialGoalResponseDto dto = new FinancialGoalResponseDto(meta);
        return dto;
    }

    // Criar uma nova meta financeira ========================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewFinancialGoalService(FinancialGoal goal) {
        financialGoalRepository.save(goal);
        return ResponseEntity.ok("Financial goal created;");
    }

    // Criar uma nova meta financeira DTO ========================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewFinancialGoalDtoService(FinancialGoalRequestDto goal) {
        User user = userRepository.findById(goal.userid()).get();
        FinancialGoal fingo = new FinancialGoal(goal.description(), goal.amount(), goal.creationDate(),
                goal.deadline());
        user.addFinancialGoalToList(fingo);
        financialGoalRepository.save(fingo);
        return ResponseEntity.ok("New financial goal created.");
    }

    // Atualizar uma meta financeira por id ==================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateFinancialGoalService(Integer id, FinancialGoal goal) {
        if (financialGoalRepository.existsById(id)) {
            goal.setId(id);
            financialGoalRepository.save(goal);
            return ResponseEntity.ok("Financial goal updated.");
        } else {
            throw new EntityNotFoundException("Financial Goal not found.");
        }
    }

    // Deletar uma meta financeira por id ================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteFinancialGoalIdService(Integer id) {
        if (financialGoalRepository.existsById(id)) {
            financialGoalRepository.deleteById(id);
            return ResponseEntity.ok("Financial goal deleted.");
        } else {
            throw new EntityNotFoundException("Financial Goal not found.");
        }
    }

    // Retorna uma lista de metas financeiras dentro de um determinado período
    public List<FinancialGoalResponseDto> getFinancialGoalsByPeriodService(LocalDate startDate, LocalDate endDate) {
        List<FinancialGoal> goals = financialGoalRepository.findByCreationDateBetween(startDate, endDate);
        return mapGoalListToGoalDtoListService(goals);
    }

    // Calcula quantos dias faltam até o prazo de uma meta específica
    public long daysUntilDeadline(Integer id) {
        FinancialGoal goal = getFinancialGoalIdService(id);
        LocalDate today = LocalDate.now();
        LocalDate deadline = goal.getDeadline();
        return ChronoUnit.DAYS.between(today, deadline);
    }

    // Retorna uma lista de metas financeiras ordenadas por valor
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderByAmountService() {
        List<FinancialGoal> goals = financialGoalRepository.findAll(Sort.by(Sort.Direction.ASC, "amount"));
        return mapGoalListToGoalDtoListService(goals);
    }

    // Retorna uma lista de metas financeiras ordenadas por prazo
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderByDeadlineService() {
        List<FinancialGoal> goals = financialGoalRepository.findAll(Sort.by(Sort.Direction.ASC, "deadline"));
        return mapGoalListToGoalDtoListService(goals);
    }

    // Retorna uma lista de metas financeiras ordenadas por tipo
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderByTypeService() {
        List<FinancialGoal> goals = financialGoalRepository.findAll(Sort.by(Sort.Direction.ASC, "type"));
        return mapGoalListToGoalDtoListService(goals);
    }

}