package com.projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetofinal.projetofinal.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
}