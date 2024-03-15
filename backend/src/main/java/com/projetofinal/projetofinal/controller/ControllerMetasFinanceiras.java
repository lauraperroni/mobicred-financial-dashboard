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

import com.projetofinal.projetofinal.dtos.MetasFinanceirasDto;
import com.projetofinal.projetofinal.model.MetasFinanceiras;
import com.projetofinal.projetofinal.service.MetasFinanceirasService;

@RestController
@RequestMapping("/metasfinanceiras")
public class ControllerMetasFinanceiras {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private MetasFinanceirasService service;

    // Endpoints ===============================================================

    // Trazer todas as metas financeiras DTO ====================================
    @GetMapping("/todas")
    public List<MetasFinanceirasDto> getAllMetasFinanceirasDto() {
        return service.getAllMetasFinanceirasDto();
    }

    // Traz uma meta financeira pelo id DTO ======================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getMetaFinanceiraId(@PathVariable Integer id) {
        MetasFinanceirasDto dto = service.getMetaFinanceiraIdDto(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar nova meta financeira ===========================================

    @PostMapping("/nova")
    public ResponseEntity<String> postNovaMetaFinanceira(@RequestBody MetasFinanceiras meta) {
        service.postNovaMetaFinanceira(meta);
        return ResponseEntity.ok("Meta financeira registrada com sucesso.");
    }

    // Update de uma meta financeira por id ========================================

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> putUpdateMetaFinanceira(@PathVariable Integer id,
            @RequestBody MetasFinanceiras meta) {
        meta.setId(id);
        return service.putUpdateMetaFinanceira(id, meta);
    }

    // Deletar uma meta financeira por id =========================================
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteMetaFinanceira(@PathVariable Integer id) {
        return service.deleteMetaFinanceiraId(id);
    }
}
