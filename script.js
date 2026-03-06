let carrinho = [];
const API_BASE_URL = 'http://localhost:8080/api';

// 1. CAPTURAR CLIQUE NOS BOTÕES "Adicionar ao carrinho"
document.addEventListener('click', (e) => {
  if (
    e.target.classList.contains('btn') &&
    e.target.innerText === 'Adicionar ao carrinho'
  ) {
    e.preventDefault();

    // Pegamos os dados do elemento pai (o box da pizza)
    const box = e.target.closest('.box');
    const nome = box.querySelector('h3').innerText;
    const precoStr = box
      .querySelector('.price')
      .innerText.split(' ')[0]
      .replace('R$', '');

    // No mundo real, usaríamos o ID que vem do banco.
    // Para este exemplo, vamos simular um ID baseado no nome ou posição.
    adicionarAoCarrinho({
      id: Math.floor(Math.random() * 10) + 1, // Simulação de ID
      nome: nome,
      preco: parseFloat(precoStr),
    });
  }
});

function adicionarAoCarrinho(produto) {
  carrinho.push(produto);
  alert(`${produto.nome} adicionada ao carrinho!`);
  console.log('Carrinho atual:', carrinho);
}

// 2. FUNÇÃO PARA ENVIAR O PEDIDO PARA O JAVA
async function finalizarPedido() {
  if (carrinho.length === 0) return alert('Seu carrinho está vazio!');

  // Montamos o objeto no formato que o PedidoController espera
  const pedidoDTO = {
    cliente: { id: 1 }, // Simula cliente logado com ID 1
    itens: carrinho.map((p) => ({
      pizza: { id: p.id },
      quantidade: 1,
    })),
  };

  try {
    const response = await fetch(`${API_BASE_URL}/pedidos`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(pedidoDTO),
    });

    if (response.ok) {
      const resultado = await response.json();
      alert(
        `Pedido #${resultado.id} realizado com sucesso! Total: R$ ${resultado.valorTotal}`,
      );
      carrinho = []; // Limpa o carrinho
    } else {
      alert('Erro ao processar pedido no servidor.');
    }
  } catch (error) {
    console.error('Erro na requisição:', error);
    alert('Não foi possível conectar ao servidor Java.');
  }
}
// Função para buscar as pizzas do Backend (Java + MySQL)
async function carregarMenuDinamico() {
  try {
    const response = await fetch(`${API_BASE_URL}/pizzas`);
    const pizzas = await response.json();

    const menuContainer = document.querySelector('.menu .box-container');

    // Limpa as pizzas estáticas que estão no HTML original
    menuContainer.innerHTML = '';

    pizzas.forEach((pizza) => {
      // Cria o HTML baseado no seu estilo CSS original
      const pizzaCard = `
                <div class="box">
                    <img src="${pizza.imagemUrl}" alt="${pizza.nome}">
                    <h3>${pizza.nome}</h3>
                    <div class="price">R$${pizza.precoAtual.toFixed(2)} 
                        ${pizza.precoAnterior ? `<span>${pizza.precoAnterior.toFixed(2)}</span>` : ''}
                    </div>
                    <a href="#" class="btn" data-id="${pizza.id}">Adicionar ao carrinho</a>
                </div>
            `;
      menuContainer.innerHTML += pizzaCard;
    });

    console.log('Menu carregado com sucesso!');
  } catch (error) {
    console.error('Erro ao carregar menu:', error);
    alert('Erro ao conectar com o servidor de pizzas.');
  }
}

// Chama a função assim que a página carregar
document.addEventListener('DOMContentLoaded', carregarMenuDinamico);
