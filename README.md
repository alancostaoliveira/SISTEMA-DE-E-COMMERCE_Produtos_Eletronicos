# SISTEMA DE E-COMMERCE – Produtos Eletrônicos

Sistema de e-commerce desenvolvido em Java com Spring Boot para gerenciamento de produtos eletrônicos.

## 🛠️ Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL (produção)
- H2 (testes)
- Maven

## 📦 Estrutura do Projeto

```
ecommerce-api/
├── src/
│   ├── main/
│   │   ├── java/         # Código-fonte principal
│   │   └── resources/    # Configurações da aplicação
│   └── test/
│       ├── java/         # Testes unitários e de integração
│       └── resources/    # Configurações de teste (H2)
└── pom.xml
```

## 🚀 Como Executar

```bash
# Clonar o repositório
git clone https://github.com/alancostaoliveira/SISTEMA-DE-E-COMMERCE_Produtos_Eletronicos.git

# Entrar na pasta do projeto
cd SISTEMA-DE-E-COMMERCE_Produtos_Eletronicos/ecommerce-api

# Rodar os testes
mvn clean test

# Iniciar a aplicação
mvn spring-boot:run
```

## 📊 Diagrama de Classes – Sistema de E-Commerce

O sistema contempla as seguintes entidades principais:

- **Produto** – Informações dos produtos eletrônicos (nome, descrição, preço, estoque)
- **Categoria** – Categorias dos produtos (smartphones, notebooks, tablets, etc.)
- **Cliente** – Dados do cliente (nome, e-mail, endereço)
- **Pedido** – Registro dos pedidos realizados
- **ItemPedido** – Itens de cada pedido (produto, quantidade, preço unitário)
- **Pagamento** – Informações de pagamento do pedido

## 📄 Licença

Este projeto está sob a licença MIT.
