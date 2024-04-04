package com.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.Transaction.TransactionResponseDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionRequestDto;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<TransactionResponseDto> getAllTransactionDto() {
        return service.getAllTransactionsDtoService();
    }

    // Traz uma transacao pelo id DTO ==============================================
    @GetMapping("/{id}")
    public TransactionResponseDto getTransactionId(@PathVariable Integer id) {
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

    // Outros endpoints

    // Endpoint para buscar e ordenar transações por preço
    @GetMapping("/search/price")
    public List<TransactionResponseDto> searchAndSortTransactionsByPrice(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        if (minPrice == null && maxPrice == null) {
            // Se nenhum filtro de preço for fornecido, retornar todas as transações
            return service.getAllTransactionsDtoService();
        }
        return service.searchAndSortTransactionsByPrice(minPrice, maxPrice);
    }

    // Endpoint para buscar e ordenar transações por data
    @GetMapping("/search/date")
    public List<TransactionResponseDto> searchAndSortTransactionsByDate(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate == null && endDate == null) {
            // Se nenhum filtro de data for fornecido, retornar todas as transações
            return service.getAllTransactionsDtoService();
        }
        return service.searchAndSortTransactionsByDate(startDate, endDate);
    }

    // Endpoint para buscar e ordenar transações por categoria
    @GetMapping("/search/category")
    public List<TransactionResponseDto> searchAndSortTransactionsByCategory(
            @RequestParam(value = "categoryId") Integer categoryId) {
        return service.searchAndSortTransactionsByCategory(categoryId);
    }

    // Endpoint para ordenar transações por preço
    @GetMapping("/sort/price")
    public List<TransactionResponseDto> sortTransactionsByPrice() {
        return service.sortTransactionsByPrice();
    }

    // Endpoint para ordenar transações por data
    @GetMapping("/sort/date")
    public List<TransactionResponseDto> sortTransactionsByDate() {
        return service.sortTransactionsByDate();
    }

    // Endpoint para ordenar transações por categoria
    @GetMapping("/sort/category")
    public List<TransactionResponseDto> sortTransactionsByCategory() {
        return service.sortTransactionsByCategory();
    }

    // Endpoint para ordenar transações por conta bancária
    @GetMapping("/sort/bankaccount/{bankAccountId}")
    public List<TransactionResponseDto> sortTransactionsByBankAccount(@PathVariable Integer bankAccountId) {
        return service.sortTransactionsByBankAccount(bankAccountId);
    }

    // Endpoint para ordenar todas as transações por conta bancária
    @GetMapping("/sort/bankaccount/all")
    public List<TransactionResponseDto> sortAllTransactionsByBankAccount() {
        return service.sortAllTransactionsByBankAccount();
    }
}
