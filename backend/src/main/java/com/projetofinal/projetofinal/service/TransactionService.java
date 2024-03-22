package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionRequestDto;
import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.repository.BankAccount.BankAccountRepository;
import com.projetofinal.projetofinal.repository.Transaction.TransactionRepository;

@Service
public class TransactionService {

    // Cria a dependencia do transactionRepository pra conversar com banco de dados
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as transacoes ===============================================
    public List<Transaction> getAllTransactionsService() {
        return transactionRepository.findAll();
    }

    // Trazer todas as transacoes Dto ===========================================

    public List<TransactionDto> getAllTransactionsDtoService() {
        List<Transaction> transactions = transactionRepository.findAll();
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
        // Faça o mesmo para outros campos
        return dto;
    }

    // Traz uma transacao pelo id ===============================================
    @SuppressWarnings("null")
    public Transaction getTransactionIdService(Integer id) {
        return transactionRepository.findById(id).get();
    }

    // Traz uma transacao pelo id DTO ===========================================
    @SuppressWarnings("null")
    public TransactionDto getTransactionIdDtoService(Integer id) {
        Transaction transaction = transactionRepository.findById(id).get(); // instancia um objeto pelo Id
        TransactionDto dto = new TransactionDto(transaction); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova transacao =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewTransactionService(Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction registered.");
    }

    // Adicionar nova transação DTO ============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewTransactionDtoService(TransactionRequestDto transaction) {
        BankAccount bank = bankAccountRepository.findById(transaction.bankAccountId()).get();
        Transaction trans = new Transaction(transaction.amount(), transaction.date());
        bank.addTransactionToList(trans);
        transactionRepository.save(trans);
        return ResponseEntity.ok("New transaction created.");
    }

    // Update de um usuário por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateTransactionService(Integer id, Transaction transaction) {
        if (transactionRepository.existsById(id)) {
            transaction.setId(id);
            transactionRepository.save(transaction);
            return ResponseEntity.ok("Transaction updated.");
        } else {
            return ResponseEntity.status(404).body("Transaction not found.");
        }
    }

    // Deletar um usuário por id ================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteTransactionIdService(Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return ResponseEntity.ok("Transaction deleted.");
        } else {
            return ResponseEntity.status(404).body("Transaction not found.");
        }
    }
}
