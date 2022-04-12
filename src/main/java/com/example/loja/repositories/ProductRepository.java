package com.example.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loja.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
