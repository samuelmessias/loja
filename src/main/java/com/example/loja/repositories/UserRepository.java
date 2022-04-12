package com.example.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loja.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
