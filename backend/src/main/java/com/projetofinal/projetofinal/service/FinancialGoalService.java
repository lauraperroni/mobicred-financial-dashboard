package com.projetofinal.projetofinal.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
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
                goal.deadline(), goal.name());
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

    // Relatórios
    // ====================================================================

    // Retorna uma lista de metas financeiras dentro de um determinado período entre
    // duas datas
    public List<FinancialGoal> getFinancialGoalsWithinPeriod(LocalDate startDate, LocalDate endDate) {
        return financialGoalRepository.findAll().stream()
                .filter(goal -> !goal.getDeadline().isBefore(startDate) && !goal.getCreationDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // Calcula quantos dias faltam até o deadline de uma meta específica
    public long getDaysUntilDeadline(Integer id) {
        @SuppressWarnings("null")
        FinancialGoal goal = financialGoalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Financial Goal not found."));
        return ChronoUnit.DAYS.between(LocalDate.now(), goal.getDeadline());
    }

    // Retorna uma lista de metas financeiras ordenadas por amount
    public List<FinancialGoal> getFinancialGoalsSortedByAmount() {
        return financialGoalRepository.findAll(Sort.by(Sort.Direction.ASC, "amount"));
    }

    // Retorna uma lista de metas financeiras ordenadas por deadline
    public List<FinancialGoal> getFinancialGoalsSortedByDeadline() {
        return financialGoalRepository.findAll(Sort.by(Sort.Direction.ASC, "deadline"));
    }

    // No seu serviço FinancialGoalService
    public List<FinancialGoal> getFinancialGoalsSortedByName() {
        List<FinancialGoal> goals = financialGoalRepository.findAll();
        goals.sort(Comparator.comparing(FinancialGoal::getName));
        return goals;
    }

    // Retorna uma lista de metas financeiras dentro de um determinado período entre
    // duas datas crescente
    public List<FinancialGoal> getFinancialGoalsBetweenDatesAscending(LocalDate startDate, LocalDate endDate) {
        return financialGoalRepository.findByDeadlineBetweenOrderByDeadlineAsc(startDate, endDate);
    }

    // Retorna uma lista de metas financeiras dentro de um determinado período entre
    // duas datas decrescente
    public List<FinancialGoal> getFinancialGoalsBetweenDatesDescending(LocalDate startDate, LocalDate endDate) {
        return financialGoalRepository.findByDeadlineBetweenOrderByDeadlineAsc(startDate, endDate);
    }

    // Calcula quantos dias faltam até o deadline de uma meta específica
    public long daysUntilDeadline(Integer id) {
        FinancialGoal goal = getFinancialGoalIdService(id);
        LocalDate deadline = goal.getDeadline();
        LocalDate currentDate = LocalDate.now();
        return currentDate.until(deadline).getDays();
    }

    // Retorna uma lista de metas financeiras ordenadas por amount (crescente)
    public List<FinancialGoal> getFinancialGoalsOrderedByAmountAscending() {
        return financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(FinancialGoal::getAmount))
                .collect(Collectors.toList());
    }

    // Retorna uma lista de metas financeiras ordenadas por amount (decrescente)
    public List<FinancialGoal> getFinancialGoalsOrderedByAmountDescending() {
        return financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(FinancialGoal::getAmount).reversed())
                .collect(Collectors.toList());
    }

    // Retorna uma lista de metas financeiras ordenadas por deadline (da mais longe
    // para a mais próxima)
    public List<FinancialGoal> getFinancialGoalsOrderedByDeadlineFarToNear() {
        return financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getDeadline).reversed())
                .collect(Collectors.toList());
    }

    // Retorna uma lista de metas financeiras ordenadas por deadline (da mais
    // próxima para a mais longe)
    public List<FinancialGoal> getFinancialGoalsOrderedByDeadlineNearToFar() {
        return financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getDeadline))
                .collect(Collectors.toList());
    }

    // Retorna uma lista de metas financeiras ordenadas por name (em ordem
    // alfabética crescente)
    public List<FinancialGoal> getFinancialGoalsOrderedByNameAscending() {
        return financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getName))
                .collect(Collectors.toList());
    }

    // Retorna uma lista de metas financeiras ordenadas por name (em ordem
    // alfabética decrescente)
    public List<FinancialGoal> getFinancialGoalsOrderedByNameDescending() {
        return financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getName).reversed())
                .collect(Collectors.toList());
    }

}