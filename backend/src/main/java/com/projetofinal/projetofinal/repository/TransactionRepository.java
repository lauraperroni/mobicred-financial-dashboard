package com.projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
