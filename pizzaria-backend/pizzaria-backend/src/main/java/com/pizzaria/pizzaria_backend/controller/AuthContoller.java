@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDTO dados) {
    Optional<Cliente> cliente = repository.findByEmail(dados.getEmail());
    
    if (cliente.isPresent() && cliente.get().getSenha().equals(dados.getSenha())) {
        // Em produção, aqui geramos um Token JWT
        return ResponseEntity.ok(cliente.get());
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
}