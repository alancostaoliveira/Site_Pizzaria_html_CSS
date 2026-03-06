package com.pizzaria.pizzaria_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzariaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzariaBackendApplication.class, args);
	}

}
@Entity
@Table(name = "tb_pizzas")
@Getter @Setter
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: "Explosão de Pepperoni"
    private String imagemUrl; // Ex: "images/pizza_item_1.jpg"
    private Double precoAtual; // Ex: 55.99
    private Double precoAnterior; // Ex: 60.99 (o valor riscado no seu CSS)
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
}