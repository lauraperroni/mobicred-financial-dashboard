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
import org.springframework.web.bind.annotation.RequestParam;
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

    // Retorna uma lista de metas financeiras dentro de um determinado período entre
    // duas datas crescente
    @GetMapping("/between-dates-ascending")
    public List<FinancialGoal> getFinancialGoalsBetweenDatesAscending(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return service.getFinancialGoalsBetweenDatesAscending(start, end);
    }

    // Retorna uma lista de metas financeiras dentro de um determinado período entre
    // duas datas decrescente
    @GetMapping("/between-dates-descending")
    public List<FinancialGoal> getFinancialGoalsBetweenDatesDescending(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return service.getFinancialGoalsBetweenDatesDescending(start, end);
    }

    // Calcula quantos dias faltam até o deadline de uma meta específica
    @GetMapping("/days-until-deadline/{id}")
    public long daysUntilDeadline(@PathVariable Integer id) {
        return service.daysUntilDeadline(id);
    }

    // Retorna uma lista de metas financeiras ordenadas por amount (crescente)
    @GetMapping("/ordered-by-amount-ascending")
    public List<FinancialGoal> getFinancialGoalsOrderedByAmountAscending() {
        return service.getFinancialGoalsOrderedByAmountAscending();
    }

    // Retorna uma lista de metas financeiras ordenadas por amount (decrescente)
    @GetMapping("/ordered-by-amount-descending")
    public List<FinancialGoal> getFinancialGoalsOrderedByAmountDescending() {
        return service.getFinancialGoalsOrderedByAmountDescending();
    }

    // Retorna uma lista de metas financeiras ordenadas por deadline (da mais longe
    // para a mais próxima)
    @GetMapping("/ordered-by-deadline-far-to-near")
    public List<FinancialGoal> getFinancialGoalsOrderedByDeadlineFarToNear() {
        return service.getFinancialGoalsOrderedByDeadlineFarToNear();
    }

    // Retorna uma lista de metas financeiras ordenadas por deadline (da mais
    // próxima para a mais longe)
    @GetMapping("/ordered-by-deadline-near-to-far")
    public List<FinancialGoal> getFinancialGoalsOrderedByDeadlineNearToFar() {
        return service.getFinancialGoalsOrderedByDeadlineNearToFar();
    }

    // Retorna uma lista de metas financeiras ordenadas por name (em ordem
    // alfabética crescente)
    @GetMapping("/ordered-by-name-ascending")
    public List<FinancialGoal> getFinancialGoalsOrderedByNameAscending() {
        return service.getFinancialGoalsOrderedByNameAscending();
    }

    // Retorna uma lista de metas financeiras ordenadas por name (em ordem
    // alfabética decrescente)
    @GetMapping("/ordered-by-name-descending")
    public List<FinancialGoal> getFinancialGoalsOrderedByNameDescending() {
        return service.getFinancialGoalsOrderedByNameDescending();
    }
}
