package com.pizzahash.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_pedidos")
@Getter @Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.RECEBIDO;

    private Double valorTotal;

    // Relacionamento com o Cliente (Muitos pedidos para um cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Itens que compõem o pedido
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itens;
}