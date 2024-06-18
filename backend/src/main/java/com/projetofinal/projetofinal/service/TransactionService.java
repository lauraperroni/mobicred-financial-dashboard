package com.projetofinal.projetofinal.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionResponseDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionPutDto;
import com.projetofinal.projetofinal.dtos.Transaction.TransactionRequestDto;
import com.projetofinal.projetofinal.exception.RestExceptionHandler;
import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.Category.Category;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.repository.BankAccount.BankAccountRepository;
import com.projetofinal.projetofinal.repository.Category.CategoryRepository;
import com.projetofinal.projetofinal.repository.Transaction.TransactionRepository;
import com.projetofinal.projetofinal.repository.User.UserRepository;

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

    @Autowired
    private UserRepository userRepository;

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
            Transaction trans = new Transaction(transactionDto.amount(), transactionDto.date(), transactionDto.type(),
                    transactionDto.description(), transactionDto.method());

            trans.setCategory(category);
            // if (transactionDto.type() == 1) { // Crédito
            // bank.deposit(transactionDto.amount(), trans);
            // } else if (transactionDto.type() == 2) { // Débito
            // bank.withdraw(transactionDto.amount(), trans);
            // }
            bank.addTransactionToList(trans);

            trans.setBankAccount(bank);

            transactionRepository.save(trans);
            bankAccountRepository.save(bank);
            return ResponseEntity.ok("New transaction created.");
        } catch (EntityNotFoundException ex) {
            return RestExceptionHandler.HandlingErrorEntityNotFound(ex);
        }
    }

    // Update de uma transação por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateTransactionService(Integer id, TransactionPutDto transaction) {
        if (transactionRepository.existsById(id)) {

            BankAccount acc = bankAccountRepository.findById(transaction.bankAccountId()).get();

            Category cat = categoryRepository.findById(transaction.categoryId()).get();

            Transaction trans = transactionRepository.findById(id).get();
            trans.putData(transaction, acc, cat);

            transactionRepository.save(trans);
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

    // Métodos extras
    // Método para ordenar transações por quantidade (amount) CRESCENTE - OK TESTADO
    public List<TransactionResponseDto> sortTransactionsByAmountAscending() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // Método para ordenar transações por quantidade (amount) DECRESCENTE - OK
    // TESTADO
    public List<TransactionResponseDto> sortTransactionsByAmountDescending() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // Método para ordenar transações por data CRESCENTE - OK TESTADO
    public List<TransactionResponseDto> sortTransactionsByDateAscending() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // Método para ordenar transações por data DECRESCENTE - OK TESTADO
    public List<TransactionResponseDto> sortTransactionsByDateDescending() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // Método para ordenar transações por categoria CRESCENTE
    public List<TransactionResponseDto> sortTransactionsByCategoryAscending() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .sorted(Comparator.comparing(transaction -> transaction.getCategory().getName()))
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // Método para ordenar transações por categoria DECRESCENTE
    public List<TransactionResponseDto> sortTransactionsByCategoryDescending() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .sorted(Comparator.comparing(transaction -> transaction.getCategory().getName(),
                        Comparator.reverseOrder()))
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // ========================================================================================
    // método para buscar transações por uma data específica - OK TESTADO
    public List<TransactionResponseDto> sortTransactionsByDate(LocalDate date) {
        List<Transaction> transactions = transactionRepository.findByDate(date);
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // ========================================================================================
    // método para buscar transações por uma categoria específica OK TESTADO
    public List<TransactionResponseDto> sortTransactionsByCategory(Integer categoryId) {
        List<Transaction> transactions = transactionRepository.findByCategoryId(categoryId);
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .map(this::mapTransactionToTransactionDtoService)
                .collect(Collectors.toList());
    }

    // ========================================================================================
    // Método para buscar transações por conta bancária - OK TESTADO
    public List<TransactionResponseDto> sortTransactionsByBankAccount(Integer bankAccountId) {
        List<Transaction> transactions = transactionRepository.findByBankAccountId(bankAccountId);
        return mapTransactionListToTransactionDtoListService(transactions);
    }

    // Método para buscar todas as transações de um usuário e mapeá-las para
    // TransactionResponseDto
    public List<TransactionResponseDto> getAllTransactionsDtoServiceByUser(User user) {
        User userEntity = (User) userRepository.findByEmail(user.getUsername());

        // Mapeia todas as transações do usuário para TransactionResponseDto
        return userEntity.getBankAccounts().stream()
                .flatMap(bankAccount -> bankAccount.getTransactions().stream())
                .map(this::mapTransactionToTransactionDtoService) // Mapeia cada transação para TransactionResponseDto
                .collect(Collectors.toList());
    }

    private TransactionResponseDto mapTransactionToTransactionDtoService(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto(transaction);
        dto.setBankAccountId(transaction.getBankAccount().getId()); // Define o ID da conta bancária
        System.out.println(transaction.getBankAccount().getId());
        dto.setCategoryId(transaction.getCategory().getId()); // Define o ID da categoria
        System.out.println(transaction.getCategory().getId());
        return dto;
    }
}
