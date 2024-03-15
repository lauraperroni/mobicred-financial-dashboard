package com.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.TransacoesDto;
import com.projetofinal.projetofinal.model.Transacoes;
import com.projetofinal.projetofinal.service.TransacoesService;
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

@RestController
@RequestMapping("/transacoes")
public class ControllerTransacoes {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private TransacoesService service;

    // Endpoints
    // =============================================================================

    // Trazer todas as transacoes DTO =============================================
    @GetMapping("/all")
    public List<TransacoesDto> getAllTransacoesDto() {
        return service.getAllTransacoesDto();
    }

    // Traz uma transacao pelo id DTO ==============================================
    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public TransacoesDto getTransacaoId(@PathVariable Integer id) {
        return service.getTransacaoIdDto(id);
    }

    // Adicionar novo usuário ======================================================
    @SuppressWarnings("null")
    @PostMapping("/nova")
    public ResponseEntity<String> postNovaTransacao(@RequestBody Transacoes transacao) {
        service.postNovaTransacao(transacao);
        return ResponseEntity.ok("Transação registrada com sucesso!");
    }

    // Update de um usuário por id ================================================
    @SuppressWarnings("null")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> putUpdateTransacao(@PathVariable Integer id, @RequestBody Transacoes transacao) {
        transacao.setId(id);
        return service.putUpdateTransacao(id, transacao);
    }

    // Deletar um usuário por id ==================================================
    @SuppressWarnings("null")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteTransacaoId(@PathVariable Integer id) {
        return service.deleteTransacaoId(id);
    }
}
