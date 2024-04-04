package com.projetofinal.projetofinal.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> postNewFinancialGoal(@RequestBody FinancialGoalRequestDto goal) {
        service.postNewFinancialGoalDtoService(goal);
        return ResponseEntity.ok("New goal created.");
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

    // Endpoint para retornar todas as metas financeiras dentro de um período
    @GetMapping("/byPeriod/{startDate}/{endDate}")
    public List<FinancialGoalResponseDto> getFinancialGoalsByPeriod(@PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate) {
        return service.getFinancialGoalsByPeriodService(startDate, endDate);
    }

    // Endpoint para calcular quantos dias faltam até o prazo de uma meta específica
    @GetMapping("/daysUntilDeadline/{id}")
    public long daysUntilDeadline(@PathVariable Integer id) {
        return service.daysUntilDeadline(id);
    }

    // Endpoint para retornar todas as metas financeiras ordenadas por valor
    @GetMapping("/orderByAmount")
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderByAmount() {
        return service.getFinancialGoalsOrderByAmountService();
    }

    // Endpoint para retornar todas as metas financeiras ordenadas por prazo
    @GetMapping("/orderByDeadline")
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderByDeadline() {
        return service.getFinancialGoalsOrderByDeadlineService();
    }

    // Endpoint para retornar todas as metas financeiras ordenadas por tipo
    @GetMapping("/orderByType")
    public List<FinancialGoalResponseDto> getFinancialGoalsOrderByType() {
        return service.getFinancialGoalsOrderByTypeService();
    }

}
