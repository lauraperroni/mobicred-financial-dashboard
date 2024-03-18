package com.projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

}
