package com.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.Transaction.TransactionResponseDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionPutDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionRequestDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.service.TransactionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
import java.util.List;
// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/transactions")
public class ControllerTransactions {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private TransactionService service;

    // Endpoints
    // =============================================================================

    // Trazer todas as transacoes DTO =============================================
    @GetMapping("/user/all")
    public List<TransactionResponseDto> getAllTransactionDto(@AuthenticationPrincipal User user) {
        return service.getAllTransactionsDtoServiceByUser(user);
    }

    // Trazer todas as transacoes DTO  de um usuário=============================================
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
    // @PutMapping("/update/{id}")
    // public ResponseEntity<String> putUpdateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
    //     transaction.setId(id);
    //     return service.putUpdateTransactionService(id, transaction);
    // }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateTransaction(@PathVariable Integer id, @RequestBody TransactionPutDto transaction) {
        return service.putUpdateTransactionService(id, transaction);
    }



    // Deletar um usuário por id ==================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransactionId(@PathVariable Integer id) {
        return service.deleteTransactionIdService(id);
    }

    // ========================================================================================

    // // Endpoint para ordenar transações por preço CRESCENTE - OK TESTADO
    // @GetMapping("/sort/price/asc")
    // public List<TransactionResponseDto> sortTransactionsByAmountAscending() {
    //     return service.sortTransactionsByAmountAscending();
    // }

    // // Endpoint para ordenar transações por preço DECRESCENTE - OK TESTADO
    // @GetMapping("/sort/price/desc")
    // public List<TransactionResponseDto> sortTransactionsByAmountDescending() {
    //     return service.sortTransactionsByAmountDescending();
    // }

    // // Endpoint para ordenar transações por data CRESCENTE - OK TESTADO
    // @GetMapping("/sort/date/asc")
    // public List<TransactionResponseDto> sortTransactionsByDateAscending() {
    //     return service.sortTransactionsByDateAscending();
    // }

    // // Endpoint para ordenar transações por data DECRESCENTE - OK TESTADO
    // @GetMapping("/sort/date/desc")
    // public List<TransactionResponseDto> sortTransactionsByDateDescending() {
    //     return service.sortTransactionsByDateDescending();
    // }

    // // Endpoint para ordenar transações por categoria CRESCENTE - OK TESTADO
    // @GetMapping("/sort/category/asc")
    // public List<TransactionResponseDto> sortTransactionsByCategoryAscending() {
    //     return service.sortTransactionsByCategoryAscending();
    // }

    // // Endpoint para ordenar transações por categoria DECRESCENTE - OK TESTADO
    // @GetMapping("/sort/category/desc")
    // public List<TransactionResponseDto> sortTransactionsByCategoryDescending() {
    //     return service.sortTransactionsByCategoryDescending();
    // }
    // // ========================================================================================

    // // Endpoint para buscar transações por data - OK TESTADO
    // @GetMapping("/sort/date")
    // public List<TransactionResponseDto> sortTransactionsByDate(@RequestBody Map<String, String> request) {
    //     String dateString = request.get("date");
    //     LocalDate parsedDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    //     return service.sortTransactionsByDate(parsedDate);
    // }

    // // Endpoint para buscar transações por categoria - OK TESTADO
    // @GetMapping("/sort/category/{categoryId}")
    // public List<TransactionResponseDto> sortTransactionsByCategory(@PathVariable Integer categoryId) {
    //     return service.sortTransactionsByCategory(categoryId);
    // }

    // // Endpoint para buscar transações por conta bancária - OK TESTADO
    // @GetMapping("/sort/bankaccount/{bankAccountId}")
    // public List<TransactionResponseDto> sortTransactionsByBankAccount(@PathVariable Integer bankAccountId) {
    //     return service.sortTransactionsByBankAccount(bankAccountId);
    // }
}
