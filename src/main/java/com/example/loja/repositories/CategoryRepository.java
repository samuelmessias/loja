package com.example.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loja.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
