package com.example.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loja.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
