package com.pizzahash.controller;

import com.pizzahash.model.Pizza;
import com.pizzahash.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin(origins = "*") // Permite que seu HTML acesse a API sem erros de CORS
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    // Rota que devolve todas as pizzas para o seu Menu
    @GetMapping
    public List<Pizza> listarTodas() {
        return repository.findAll();
    }

    // Rota para cadastrar uma nova pizza (útil para o futuro painel administrativo)
    @PostMapping
    public Pizza salvar(@RequestBody Pizza pizza) {
        return repository.save(pizza);
    }
}