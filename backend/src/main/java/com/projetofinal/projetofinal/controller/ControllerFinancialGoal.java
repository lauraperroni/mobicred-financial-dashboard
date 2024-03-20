package com.projetofinal.projetofinal.controller;

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

import com.projetofinal.projetofinal.dtos.FinancialGoal.FinancialGoalDto;
import com.projetofinal.projetofinal.model.FinancialGoal;
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
    public List<FinancialGoalDto> getAllFinancialGoalDto() {
        return service.getAllFinancialGoalDtoService();
    }

    // Traz uma meta financeira pelo id DTO ======================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getFinancialGoalId(@PathVariable Integer id) {
        FinancialGoalDto dto = service.getFinancialGoalIdDtoService(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar nova meta financeira ===========================================

    @PostMapping("/new")
    public ResponseEntity<String> postNovaMetaFinanceira(@RequestBody FinancialGoal goal) {
        service.postNewFinancialGoalService(goal);
        return ResponseEntity.ok("New goal created.");
    }

    // Update de uma meta financeira por id ========================================

    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateMetaFinanceira(@PathVariable Integer id,
            @RequestBody FinancialGoal goal) {
        goal.setId(id);
        return service.putUpdateFinancialGoalService(id, goal);
    }

    // Deletar uma meta financeira por id =========================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFinancialGoalId(@PathVariable Integer id) {
        return service.deleteFinancialGoalIdService(id);
    }
}
