@Entity
@Table(name = "tb_itens_pedido")
@Getter @Setter
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer quantidade;
    private Double precoUnitario;

    @ManyToOne
    private Pizza pizza;
}