package com.projetofinal.projetofinal.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalResponseDto;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalPutDto;
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

    // Trazer todas as metas financeiras Dto by user
    // ====================================
    public List<FinancialGoalResponseDto> getAllFinancialGoalDtoServiceByUser(User user) {
        User user2 = (User) userRepository.findByEmail(user.getUsername());
        List<FinancialGoal> goals = financialGoalRepository.findByUserId(user2.getId());
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
    public ResponseEntity<String> postNewFinancialGoalDtoService(String userName, FinancialGoalRequestDto goal) {

        // User user = userRepository.findById(goal.userid()).get();
        // FinancialGoal fingo = new FinancialGoal(goal.description(), goal.amount(),
        // goal.creationDate(),
        // goal.deadline(), goal.name(), goal.type(), goal.saved());
        // user.addFinancialGoalToList(fingo);
        // financialGoalRepository.save(fingo);
        // return ResponseEntity.ok("New financial goal created.");

        FinancialGoal goal1 = new FinancialGoal(goal);
        User user = (User) userRepository.findByEmail(userName);
        goal.setUser(user);
        user.addFinancialGoalToList(goal1);

        financialGoalRepository.save(goal1);
        return ResponseEntity.ok("Nova meta criada.");
    }



    // Atualizar uma meta financeira por id ==================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateFinancialGoalServiceTeste(Integer id, FinancialGoalPutDto goal) {
        if (financialGoalRepository.existsById(id)) {
            FinancialGoal meta = financialGoalRepository.findById(id).get();
            meta.putData(goal);
        
            financialGoalRepository.save(meta);
            return ResponseEntity.ok("Financial goal updated.");
        } else {
            System.out.println("Tentando service else");        
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

    // Retorna todas as metas financeiras ordenadas por quantidade em ordem
    // ascendente - OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByAmountAscending() {
        List<FinancialGoal> financialGoals = financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(FinancialGoal::getAmount))
                .collect(Collectors.toList());
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

    // Retorna todas as metas financeiras ordenadas por quantidade em ordem
    // descendente - OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByAmountDescending() {
        List<FinancialGoal> financialGoals = financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(FinancialGoal::getAmount).reversed())
                .collect(Collectors.toList());
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

    // ====================================

    // Método para retornar todas as metas financeiras ordenadas pelo prazo de perto
    // para longe - OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByDeadlineAscending() {
        List<FinancialGoal> financialGoals = financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getDeadline))
                .collect(Collectors.toList());
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

    // Método para retornar todas as metas financeiras ordenadas pelo prazo de longe
    // para perto - OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByDeadlineDescending() {
        List<FinancialGoal> financialGoals = financialGoalRepository.findAll()
                .stream()
                .filter(goal -> Objects.nonNull(goal.getDeadline())) // Verificar se o prazo de vencimento não é nulo
                .sorted(Comparator.comparing(FinancialGoal::getDeadline).reversed()) // Ordenar de forma descendente
                .collect(Collectors.toList());
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

    // Método para retornar todas as metas financeiras ordenadas pelo nome em ordem
    // ascendente - OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByNameAscending() {
        List<FinancialGoal> financialGoals = financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getName)) // Ordenar pelo nome em ordem ascendente
                .collect(Collectors.toList());
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

    // Método para retornar todas as metas financeiras ordenadas pelo nome em ordem
    // descendente - OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByNameDescending() {
        List<FinancialGoal> financialGoals = financialGoalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(FinancialGoal::getName).reversed()) // Ordenar pelo nome em ordem
                                                                                 // descendente
                .collect(Collectors.toList());
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

    // ==================================================================

    // Método para retornar todas as metas financeiras com um nome específico - OK
    // TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsByName(String name) {
        List<FinancialGoal> financialGoals = financialGoalRepository.findByName(name);
        return financialGoals.stream()
                .map(FinancialGoalResponseDto::new)
                .collect(Collectors.toList());
    }

}