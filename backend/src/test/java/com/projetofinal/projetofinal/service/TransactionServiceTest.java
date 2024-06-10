package com.projetofinal.projetofinal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.projetofinal.projetofinal.dtos.Transaction.TransactionResponseDto;
import com.projetofinal.projetofinal.model.Transaction.Transaction;
import com.projetofinal.projetofinal.repository.BankAccount.BankAccountRepository;
import com.projetofinal.projetofinal.repository.Category.CategoryRepository;
import com.projetofinal.projetofinal.repository.Transaction.TransactionRepository;

@SpringBootTest
public class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testGetAllTransactionsDtoService() {
        // Mock de dados de transações
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(100.0, LocalDate.now(), 1, null, null));
        transactions.add(new Transaction(200.0, LocalDate.now(), 2, null, null));

        // Configurando o comportamento do mock do repositório
        when(transactionRepository.findAll()).thenReturn(transactions);

        // Chamando o método a ser testado
        List<TransactionResponseDto> transactionDtos = transactionService.getAllTransactionsDtoService();

        // Verificação
        assertEquals(transactions.size(), transactionDtos.size());
        assertEquals(transactions.get(0).getAmount(), transactionDtos.get(0).getAmount());
        assertEquals(transactions.get(1).getAmount(), transactionDtos.get(1).getAmount());
    }

    @Test
    void testGetTransactionIdService() {
        // Mock de dados de transação
        Transaction transaction = new Transaction(100.0, LocalDate.now(), 1, null, null);

        // Configurando o comportamento do mock do repositório
        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));

        // Chamando o método a ser testado
        Transaction fetchedTransaction = transactionService.getTransactionIdService(1);

        // Verificação
        assertEquals(transaction.getId(), fetchedTransaction.getId());
        assertEquals(transaction.getAmount(), fetchedTransaction.getAmount());
    }
}