# 🛒 E-commerce API — Produtos Eletrônicos

Sistema de e-commerce desenvolvido com **Java 17 + Spring Boot**, focado no gerenciamento de produtos eletrônicos, pedidos, clientes, pagamentos e avaliações.

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.4 |
| Spring Data JPA | 3.2.4 |
| Hibernate | (gerenciado pelo Spring Boot) |
| Lombok | latest |
| PostgreSQL | 15 |
| Docker | latest |

## 📁 Estrutura do Projeto

```
ecommerce-api/
│
├── src/main/java/com/ecommerce/
│   ├── config/
│   ├── controller/
│   │   ├── AvaliacaoController.java
│   │   ├── CategoriaController.java
│   │   ├── ClienteController.java
│   │   ├── MarcaController.java
│   │   ├── PagamentoController.java
│   │   ├── PedidoController.java
│   │   └── ProdutoController.java
│   ├── service/
│   │   ├── AvaliacaoService.java
│   │   ├── CategoriaService.java
│   │   ├── ClienteService.java
│   │   ├── MarcaService.java
│   │   ├── PagamentoService.java
│   │   ├── PedidoService.java
│   │   └── ProdutoService.java
│   ├── repository/
│   │   ├── AvaliacaoRepository.java
│   │   ├── CategoriaRepository.java
│   │   ├── ClienteRepository.java
│   │   ├── MarcaRepository.java
│   │   ├── PagamentoRepository.java
│   │   ├── PedidoRepository.java
│   │   └── ProdutoRepository.java
│   ├── domain/
│   │   ├── model/
│   │   │   ├── Avaliacao.java
│   │   │   ├── Categoria.java
│   │   │   ├── Cliente.java
│   │   │   ├── ItemPedido.java
│   │   │   ├── Marca.java
│   │   │   ├── Pagamento.java
│   │   │   ├── Pedido.java
│   │   │   └── Produto.java
│   │   ├── enums/
│   │   │   ├── FormaPagamento.java
│   │   │   ├── StatusPagamento.java
│   │   │   └── StatusPedido.java
│   │   └── dto/
│   │       ├── AvaliacaoDTO.java
│   │       ├── CategoriaDTO.java
│   │       ├── ClienteDTO.java
│   │       ├── ItemPedidoDTO.java
│   │       ├── MarcaDTO.java
│   │       ├── PagamentoDTO.java
│   │       ├── PedidoDTO.java
│   │       └── ProdutoDTO.java
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java
│   │   ├── RecursoNaoEncontradoException.java
│   │   └── RegraDeNegocioException.java
│   └── EcommerceApplication.java
│
├── src/main/resources/
│   └── application.yml
│
├── src/test/
│   ├── java/com/ecommerce/
│   │   └── EcommerceApplicationTests.java
│   └── resources/
│       └── application.yml
│
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## 📊 Funcionalidades

- ✅ **Cadastro de Produtos** — com categoria, marca, preço e estoque
- ✅ **Gerenciamento de Categorias** — CRUD completo
- ✅ **Gerenciamento de Marcas** — CRUD completo
- ✅ **Cadastro de Clientes** — com validação de email e CPF únicos
- ✅ **Gerenciamento de Pedidos** — criação com controle de estoque automático, cancelamento com devolução ao estoque
- ✅ **Processamento de Pagamentos** — múltiplas formas (PIX, cartão, boleto)
- ✅ **Sistema de Avaliações** — notas e comentários por produto
- ✅ **Tratamento de Erros** — respostas padronizadas para erros de validação e de negócio

## 🔗 Endpoints da API

### Produtos
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/produtos` | Listar todos os produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| GET | `/produtos/categoria/{id}` | Filtrar por categoria |
| GET | `/produtos/marca/{id}` | Filtrar por marca |
| GET | `/produtos/buscar?nome=` | Pesquisar por nome |
| GET | `/produtos/em-estoque` | Listar produtos com estoque |
| POST | `/produtos` | Criar produto |
| PUT | `/produtos/{id}` | Atualizar produto |
| PATCH | `/produtos/{id}/estoque?quantidade=` | Ajustar estoque |
| DELETE | `/produtos/{id}` | Remover produto |

### Categorias
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/categorias` | Listar categorias |
| GET | `/categorias/{id}` | Buscar por ID |
| POST | `/categorias` | Criar categoria |
| PUT | `/categorias/{id}` | Atualizar categoria |
| DELETE | `/categorias/{id}` | Remover categoria |

### Marcas
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/marcas` | Listar marcas |
| GET | `/marcas/{id}` | Buscar por ID |
| POST | `/marcas` | Criar marca |
| PUT | `/marcas/{id}` | Atualizar marca |
| DELETE | `/marcas/{id}` | Remover marca |

### Clientes
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Buscar por ID |
| POST | `/clientes` | Cadastrar cliente |
| PUT | `/clientes/{id}` | Atualizar cliente |
| DELETE | `/clientes/{id}` | Remover cliente |

### Pedidos
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/pedidos` | Listar pedidos |
| GET | `/pedidos/{id}` | Buscar por ID |
| GET | `/pedidos/cliente/{id}` | Pedidos do cliente |
| GET | `/pedidos/status/{status}` | Filtrar por status |
| POST | `/pedidos` | Criar pedido |
| PATCH | `/pedidos/{id}/status?status=` | Atualizar status |
| POST | `/pedidos/{id}/cancelar` | Cancelar pedido |

### Pagamentos
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/pagamentos` | Listar pagamentos |
| GET | `/pagamentos/{id}` | Buscar por ID |
| POST | `/pagamentos` | Processar pagamento |

### Avaliações
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/avaliacoes/produto/{id}` | Avaliações do produto |
| GET | `/avaliacoes/produto/{id}/media` | Média de notas |
| POST | `/avaliacoes` | Criar avaliação |
| DELETE | `/avaliacoes/{id}` | Remover avaliação |

## ▶️ Como Rodar

### Com Docker Compose (recomendado)

```bash
cd ecommerce-api
docker-compose up -d
```

A API estará disponível em: `http://localhost:8080`

### Executando Localmente

**Pré-requisitos:** Java 17+, Maven, PostgreSQL rodando na porta 5432

```bash
cd ecommerce-api
./mvnw spring-boot:run
```

### Executando os Testes

```bash
cd ecommerce-api
./mvnw test
```

## ⚙️ Configuração

O arquivo `src/main/resources/application.yml` define as configurações padrão para PostgreSQL:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce
    username: postgres
    password: postgres
```

As variáveis de ambiente `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD` sobrescrevem os valores padrão (usadas no Docker Compose).

## 📦 Exemplos de Payload

### Criar Produto
```json
POST /produtos
{
  "nome": "Smartphone XYZ Pro",
  "descricao": "Smartphone de última geração com 256GB",
  "preco": 2999.99,
  "estoque": 50,
  "categoriaId": 1,
  "marcaId": 1
}
```

### Criar Pedido
```json
POST /pedidos
{
  "clienteId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 2 },
    { "produtoId": 3, "quantidade": 1 }
  ]
}
```

### Processar Pagamento
```json
POST /pagamentos
{
  "pedidoId": 1,
  "formaPagamento": "PIX"
}
```

## 🗂️ Diagrama de Classes

```
DIAGRAMA DE CLASSES – SISTEMA DE E-COMMERCE

Categoria (1) ──────── (N) Produto (N) ──────── (1) Marca
                              │
                         (N) ItemPedido (N) ──── (1) Pedido (N) ──── (1) Cliente
                                                       │
                                                  (1) Pagamento
                              │
                         (N) Avaliacao ──────────────────────────── (1) Cliente
```

