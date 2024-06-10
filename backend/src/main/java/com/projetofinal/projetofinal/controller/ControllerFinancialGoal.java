package com.projetofinal.projetofinal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalResponseDto;
import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalRequestDto;
import com.projetofinal.projetofinal.model.FinancialGoal.FinancialGoal;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.service.FinancialGoalService;

@RestController
@RequestMapping("/financialgoals")
public class ControllerFinancialGoal {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private FinancialGoalService service;

    // Endpoints ===============================================================

    // Trazer todas as metas financeiras DTO ====================================
    @GetMapping("/all")
    public List<FinancialGoalResponseDto> getAllFinancialGoalDto() {
        return service.getAllFinancialGoalDtoService();
    }

    // Trazer todas as metas financeiras DTO de um usuário
    // ====================================
    @GetMapping("/user/all")
    public List<FinancialGoalResponseDto> getAllFinancialGoalDtoByUser(@AuthenticationPrincipal User user) {
        return service.getAllFinancialGoalDtoServiceByUser(user);
    }

    // Traz uma meta financeira pelo id DTO ======================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getFinancialGoalId(@PathVariable Integer id) {
        FinancialGoalResponseDto dto = service.getFinancialGoalIdDtoService(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar nova meta financeira ===========================================

    @PostMapping("/new")
    public ResponseEntity<String> postNewFinancialGoal(@AuthenticationPrincipal User user,
            @RequestBody FinancialGoalRequestDto goal) {
        try {
            String email = user.getUsername();
            service.postNewFinancialGoalDtoService(email, goal);
            return ResponseEntity.ok("Nova meta registrada.");
        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao criar nova meta.");
        }
    }

    // Update de uma meta financeira por id ========================================

    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateFinancialGoal(@PathVariable Integer id,
            @RequestBody FinancialGoal goal) {
        goal.setId(id);
        return service.putUpdateFinancialGoalService(id, goal);
    }

    // Deletar uma meta financeira por id =========================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFinancialGoalId(@PathVariable Integer id) {
        return service.deleteFinancialGoalIdService(id);
    }

    // Relatórios
    // =======================================================================

    @GetMapping("/sort/amount/asc") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByAmountAscending() {
        return service.getFinancialGoalsOrderedByAmountAscending();
    }

    @GetMapping("/sort/amount/desc") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByAmountDescending() {
        return service.getFinancialGoalsOrderedByAmountDescending();
    }

    // ================================

    // Endpoint para retornar todas as metas financeiras ordenadas pelo prazo de
    // vencimento de forma ascendente
    @GetMapping("/sort/deadline/asc") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByDeadlineAscending() {
        return service.getFinancialGoalsOrderedByDeadlineAscending();
    }

    // Endpoint para retornar todas as metas financeiras ordenadas pelo prazo de
    // vencimento de forma descendente
    @GetMapping("/sort/deadline/desc") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByDeadlineDescending() {
        return service.getFinancialGoalsOrderedByDeadlineDescending();
    }

    // Endpoint para retornar todas as metas financeiras ordenadas pelo nome em
    // ordem ascendente
    @GetMapping("/sort/name/asc") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByNameAscending() {
        return service.getFinancialGoalsOrderedByNameAscending();
    }

    // Endpoint para retornar todas as metas financeiras ordenadas pelo nome em
    // ordem descendente
    @GetMapping("/sort/name/desc") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderedByNameDescending() {
        return service.getFinancialGoalsOrderedByNameDescending();
    }

    // =======================================================

    @GetMapping("/search/name/{name}") // OK TESTADO E FUNCIONANDO
    public List<FinancialGoalResponseDto> getFinancialGoalsByName(@PathVariable String name) {
        return service.getFinancialGoalsByName(name);
    }
}