package com.pizzahash.repository;

import com.pizzahash.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    // Aqui o Spring já cria automaticamente métodos como save(), findAll() e delete()
}