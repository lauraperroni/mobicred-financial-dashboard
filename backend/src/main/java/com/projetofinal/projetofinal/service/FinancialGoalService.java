package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalDto;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalRequestDto;
import com.projetofinal.projetofinal.model.FinancialGoal;
import com.projetofinal.projetofinal.model.User;
import com.projetofinal.projetofinal.repository.FinancialGoal.FinancialGoalRepository;
import com.projetofinal.projetofinal.repository.User.UserRepository;

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
    public List<FinancialGoalDto> getAllFinancialGoalDtoService() {
        List<FinancialGoal> goals = financialGoalRepository.findAll();
        return mapGoalListToGoalDtoListService(goals);
    }

    // Pega os objetos transformados em DTO e cria uma lista
    public List<FinancialGoalDto> mapGoalListToGoalDtoListService(List<FinancialGoal> goals) {
        return goals.stream()
                .map(this::mapGoalToGoalDtoService)
                .collect(Collectors.toList());
    }

    // Transforma os objetos model em DTO
    public FinancialGoalDto mapGoalToGoalDtoService(FinancialGoal goal) {
        FinancialGoalDto dto = new FinancialGoalDto();
        // Mapeie os campos para o DTO conforme necessário
        dto.setIdDto(goal.getId());
        dto.setDescriptionDto(goal.getDescription());
        dto.setAmountDto(goal.getAmount());
        dto.setDateDto(goal.getDate());
        return dto;
    }

    // Trazer uma meta financeira pelo id ====================================
    @SuppressWarnings("null")
    public FinancialGoal getFinancialGoalIdService(Integer id) {
        return financialGoalRepository.findById(id).get();
    }

    // Trazer metas financeiras pelo id Dto ==================================
    public FinancialGoalDto getFinancialGoalIdDtoService(Integer id) {
        @SuppressWarnings("null")
        FinancialGoal meta = financialGoalRepository.findById(id).get();
        FinancialGoalDto dto = new FinancialGoalDto(meta);
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
        FinancialGoal fingo = new FinancialGoal(goal.description(), goal.amount(), goal.date());
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
            return ResponseEntity.status(404).body("Financial goal not found.");
        }
    }

    // Deletar uma meta financeira por id ================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteFinancialGoalIdService(Integer id) {
        if (financialGoalRepository.existsById(id)) {
            financialGoalRepository.deleteById(id);
            return ResponseEntity.ok("Financial goal deleted.");
        } else {
            return ResponseEntity.status(404).body("Financial goal not found.");
        }
    }
}