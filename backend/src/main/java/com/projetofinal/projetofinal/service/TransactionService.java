package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionResponseDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionRequestDto;
import com.projetofinal.projetofinal.exception.RestExceptionHandler;
import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.Category.Category;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.repository.BankAccount.BankAccountRepository;
import com.projetofinal.projetofinal.repository.Category.CategoryRepository;
import com.projetofinal.projetofinal.repository.Transaction.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {

    // Cria a dependencia do transactionRepository pra conversar com banco de dados
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as transacoes ===============================================
    public List<Transaction> getAllTransactionsService() {
        return transactionRepository.findAll();
    }

    // Trazer todas as transacoes Dto ===========================================

    public List<TransactionResponseDto> getAllTransactionsDtoService() {
        List<Transaction> transactions = transactionRepository.findAll();
        return mapTransactionListToTransactionDtoListService(transactions);
    }

    // Método para converter uma lista de objetos Transacoes em uma lista de objetos
    // TransacoesDto
    private List<TransactionResponseDto> mapTransactionListToTransactionDtoListService(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::mapTransactionToTransactionDtoService) // usando o método abaixo
                .collect(Collectors.toList());
    }

    // Converte uma Transacao em uma TransacaoDto
    private TransactionResponseDto mapTransactionToTransactionDtoService(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto(transaction);
        return dto;
    }

    // Traz uma transacao pelo id ===============================================
    @SuppressWarnings("null")
    public Transaction getTransactionIdService(Integer id) {
        return transactionRepository.findById(id).get();
    }

    // Traz uma transacao pelo id DTO ===========================================
    @SuppressWarnings("null")
    public TransactionResponseDto getTransactionIdDtoService(Integer id) {
        Transaction transaction = transactionRepository.findById(id).get(); // instancia um objeto pelo Id
        TransactionResponseDto dto = new TransactionResponseDto(transaction); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova transacao =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewTransactionService(Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction registered.");
    }

    // Adicionar nova transação DTO ============================================
    @SuppressWarnings({ "null", "unchecked" })
    public ResponseEntity<String> postNewTransactionDtoService(TransactionRequestDto transactionDto) {
        try {
            BankAccount bank = bankAccountRepository.findById(transactionDto.bankAccountId())
                    .orElseThrow(EntityNotFoundException::new);
            Category category = categoryRepository.findById(transactionDto.categoryId())
                    .orElseThrow(EntityNotFoundException::new);
            Transaction trans = new Transaction(transactionDto.amount(), transactionDto.date());

            trans.setCategory(category);
            if (transactionDto.type() == 1) { // Crédito
                bank.deposit(transactionDto.amount());
            } else if (transactionDto.type() == 2) { // Débito
                bank.withdraw(transactionDto.amount());
            }

            bank.addTransactionToList(trans);
            transactionRepository.save(trans);
            bankAccountRepository.save(bank);
            return ResponseEntity.ok("New transaction created.");
        } catch (EntityNotFoundException ex) {
            return RestExceptionHandler.HandlingErrorEntityNotFound(ex);
        }
    }

    // Update de um usuário por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateTransactionService(Integer id, Transaction transaction) {
        if (transactionRepository.existsById(id)) {
            transaction.setId(id);
            transactionRepository.save(transaction);
            return ResponseEntity.ok("Transaction updated.");
        } else {
            throw new EntityNotFoundException("Transaction not found.");
        }
    }

    // Deletar um usuário por id ================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteTransactionIdService(Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return ResponseEntity.ok("Transaction deleted.");
        } else {
            throw new EntityNotFoundException("Transaction not found.");
        }
    }
}
