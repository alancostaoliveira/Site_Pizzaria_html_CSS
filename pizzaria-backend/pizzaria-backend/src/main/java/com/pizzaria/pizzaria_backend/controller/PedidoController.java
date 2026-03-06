@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido realizarPedido(@RequestBody Pedido pedido) {
        return service.finalizarPedido(pedido);
    }
}