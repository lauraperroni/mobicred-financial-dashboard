package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.TransactionDto;
import com.projetofinal.projetofinal.model.Transaction;
import com.projetofinal.projetofinal.repository.TransactionRepository;

@Service
public class TransactionService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private TransactionRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as transacoes ===============================================
    public List<Transaction> getAllTransactionsService() {
        return repository.findAll();
    }

    // Trazer todas as transacoes Dto ===========================================

    public List<TransactionDto> getAllTransactionsDtoService() {
        List<Transaction> transactions = repository.findAll();
        return mapTransactionListToTransactionDtoListService(transactions);
    }

    // Método para converter uma lista de objetos Transacoes em uma lista de objetos
    // TransacoesDto
    private List<TransactionDto> mapTransactionListToTransactionDtoListService(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::mapTransactionToTransactionDtoService) // usando o método abaixo
                .collect(Collectors.toList());
    }

    // Converte uma Transacao em uma TransacaoDto
    private TransactionDto mapTransactionToTransactionDtoService(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        // Mapeie os campos do usuário para o DTO conforme necessário
        dto.setIdDto(transaction.getId());
        dto.setAmountDto(transaction.getAmount());
        dto.setCategoryIdDto(transaction.getCategoryId());
        // Faça o mesmo para outros campos
        return dto;
    }

    // Traz uma transacao pelo id ===============================================
    @SuppressWarnings("null")
    public Transaction getTransactionIdService(Integer id) {
        return repository.findById(id).get();
    }

    // Traz uma transacao pelo id DTO ===========================================
    @SuppressWarnings("null")
    public TransactionDto getTransactionIdDtoService(Integer id) {
        Transaction transaction = repository.findById(id).get(); // instancia um objeto pelo Id
        TransactionDto dto = new TransactionDto(transaction); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova transacao =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewTransactionService(Transaction transaction) {
        repository.save(transaction);
        return ResponseEntity.ok("Transaction registered.");
    }

    // Update de um usuário por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateTransactionService(Integer id, Transaction transaction) {
        if (repository.existsById(id)) {
            transaction.setId(id);
            repository.save(transaction);
            return ResponseEntity.ok("Transaction updated.");
        } else {
            return ResponseEntity.status(404).body("Transaction not found.");
        }
    }

    // Deletar um usuário por id ================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteTransactionIdService(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Transaction deleted.");
        } else {
            return ResponseEntity.status(404).body("Transaction not found.");
        }
    }
}
