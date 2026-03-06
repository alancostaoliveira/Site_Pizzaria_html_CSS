@Entity
@Table(name = "tb_clientes")
@Getter @Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
    private String senha; // Lembre-se: em produção, nunca guarde senhas sem criptografia!
    private String endereco;
    private String telefone;
}