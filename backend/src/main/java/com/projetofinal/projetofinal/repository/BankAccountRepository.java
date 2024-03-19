package com.projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

}
