package com.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.Transaction.TransactionDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionRequestDto;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.service.TransactionService;

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
@RequestMapping("/transactions")
public class ControllerTransactions {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private TransactionService service;

    // Endpoints
    // =============================================================================

    // Trazer todas as transacoes DTO =============================================
    @GetMapping("/all")
    public List<TransactionDto> getAllTransactionDto() {
        return service.getAllTransactionsDtoService();
    }

    // Traz uma transacao pelo id DTO ==============================================
    @GetMapping("/{id}")
    public TransactionDto getTransactionId(@PathVariable Integer id) {
        return service.getTransactionIdDtoService(id);
    }

    // Adicionar nova transação
    // =======================================================
    @PostMapping("/new")
    public ResponseEntity<String> postNewTransaction(@RequestBody TransactionRequestDto transaction) {
        service.postNewTransactionDtoService(transaction);
        return ResponseEntity.ok("New transaction registered.");
    }

    // Update de um usuário por id ================================================
    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return service.putUpdateTransactionService(id, transaction);
    }

    // Deletar um usuário por id ==================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransactionId(@PathVariable Integer id) {
        return service.deleteTransactionIdService(id);
    }
}
