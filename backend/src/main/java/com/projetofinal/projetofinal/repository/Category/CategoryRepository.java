package com.projetofinal.projetofinal.repository.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.projetofinal.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
