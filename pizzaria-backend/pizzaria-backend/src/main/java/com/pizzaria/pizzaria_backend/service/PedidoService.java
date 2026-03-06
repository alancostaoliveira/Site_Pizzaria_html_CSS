package com.pizzahash.service;

import com.pizzahash.model.ItemPedido;
import com.pizzahash.model.Pedido;
import com.pizzahash.model.Pizza;
import com.pizzahash.repository.PedidoRepository;
import com.pizzahash.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional
    public Pedido finalizarPedido(Pedido pedido) {
        double total = 0.0;

        // Regra de Negócio: Percorrer itens e calcular valor real
        for (ItemPedido item : pedido.getItens()) {
            // Buscamos a pizza no banco para garantir o preço oficial
            Pizza pizza = pizzaRepository.findById(item.getPizza().getId())
                    .orElseThrow(() -> new RuntimeException("Pizza não encontrada"));
            
            item.setPrecoUnitario(pizza.getPrecoAtual());
            total += item.getPrecoUnitario() * item.getQuantidade();
        }

        pedido.setValorTotal(total);
        return pedidoRepository.save(pedido);
    }
}