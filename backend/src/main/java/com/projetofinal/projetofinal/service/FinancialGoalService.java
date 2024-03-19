package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.FinancialGoalDto;
import com.projetofinal.projetofinal.model.FinancialGoal;
import com.projetofinal.projetofinal.repository.FinancialGoalRepository;

@Service
public class FinancialGoalService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private FinancialGoalRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as metas financeiras ========================================
    public List<FinancialGoal> getAllFinancialGoalService() {
        return repository.findAll();
    }

    // Trazer todas as metas financeiras Dto ====================================
    public List<FinancialGoalDto> getAllFinancialGoalDtoService() {
        List<FinancialGoal> metas = repository.findAll();
        return mapGoalListToGoalDtoListService(metas);
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
        dto.setDescriptionDto(goal.getDescription());
        dto.setAmountDto(goal.getAmount());
        dto.setDateDto(goal.getDate());
        return dto;
    }

    // Trazer uma meta financeira pelo id ====================================
    @SuppressWarnings("null")
    public FinancialGoal getFinancialGoalIdService(Integer id) {
        return repository.findById(id).get();
    }

    // Trazer metas financeiras pelo id Dto ==================================
    public FinancialGoalDto getFinancialGoalIdDtoService(Integer id) {
        @SuppressWarnings("null")
        FinancialGoal meta = repository.findById(id).get();
        FinancialGoalDto dto = new FinancialGoalDto(meta);
        return dto;
    }

    // Criar uma nova meta financeira ========================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewFinancialGoalService(FinancialGoal goal) {
        repository.save(goal);
        return ResponseEntity.ok("Financial goal created;");
    }

    // Atualizar uma meta financeira por id ==================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateFinancialGoalService(Integer id, FinancialGoal goal) {
        if (repository.existsById(id)) {
            goal.setId(id);
            repository.save(goal);
            return ResponseEntity.ok("Financial goal updated.");
        } else {
            return ResponseEntity.status(404).body("Financial goal not found.");
        }
    }

    // Deletar uma meta financeira por id ================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteFinancialGoalIdService(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Financial goal deleted.");
        } else {
            return ResponseEntity.status(404).body("Financial goal not found.");
        }
    }
}
