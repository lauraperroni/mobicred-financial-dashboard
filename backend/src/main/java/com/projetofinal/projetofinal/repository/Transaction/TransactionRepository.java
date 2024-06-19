package com.projetofinal.projetofinal.repository.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.dtos.Transaction.TransactionResponseDto;
import com.projetofinal.projetofinal.model.Transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByDate(LocalDate date);

    // List<Transaction> findByCategoryId(Integer categoryId);

    // List<Transaction> findByBankAccountId(Integer bankAccountId);

    List<TransactionResponseDto> findAllTransactionsByBankAccountUserId(Integer id);

}
