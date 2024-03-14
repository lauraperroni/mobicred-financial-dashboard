package com.projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.Transacoes;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Integer> {

}
