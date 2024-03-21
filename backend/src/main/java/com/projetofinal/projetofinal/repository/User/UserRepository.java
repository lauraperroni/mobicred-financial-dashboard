package com.projetofinal.projetofinal.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.User.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}