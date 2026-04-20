# Documentação

## Diagrama de Classes

```
┌─────────────┐         ┌─────────────┐
│  Categoria  │         │    Marca    │
├─────────────┤         ├─────────────┤
│ id: Long    │         │ id: Long    │
│ nome: String│         │ nome: String│
│ descricao   │         │ descricao   │
└──────┬──────┘         │ paisOrigem  │
       │ 1              └──────┬──────┘
       │                       │ 1
       │ N                     │ N
       └───────────┬───────────┘
                   │
            ┌──────┴──────┐
            │   Produto   │
            ├─────────────┤
            │ id: Long    │
            │ nome: String│
            │ descricao   │
            │ preco: BD   │
            │ estoque: Int│
            └──────┬──────┘
                   │ 1
         ┌─────────┴──────────┐
         │ N                  │ N
  ┌──────┴──────┐     ┌───────┴──────┐
  │ ItemPedido  │     │  Avaliacao   │
  ├─────────────┤     ├──────────────┤
  │ id: Long    │     │ id: Long     │
  │ quantidade  │     │ nota: Int    │
  │ valorUnit.  │     │ comentario   │
  │ subtotal    │     │ data         │
  └──────┬──────┘     └───────┬──────┘
         │ N                  │ N
         │                    │
  ┌──────┴──────┐      ┌──────┴──────┐
  │   Pedido    │      │   Cliente   │
  ├─────────────┤      ├─────────────┤
  │ id: Long    │      │ id: Long    │
  │ dataPedido  │      │ nome: String│
  │ status      │      │ email       │
  │ valorTotal  ├──N──1┤ cpf         │
  └──────┬──────┘      │ telefone    │
         │ 1           │ endereco    │
         │             └─────────────┘
  ┌──────┴──────┐
  │  Pagamento  │
  ├─────────────┤
  │ id: Long    │
  │ forma       │
  │ status      │
  │ valor: BD   │
  │ data        │
  └─────────────┘
```

## Status do Pedido

```
AGUARDANDO_PAGAMENTO → PAGO → EM_SEPARACAO → EM_TRANSPORTE → ENTREGUE
                  ↘ CANCELADO ↗
```

## Formas de Pagamento

- `CARTAO_CREDITO`
- `CARTAO_DEBITO`
- `PIX`
- `BOLETO`
- `TRANSFERENCIA_BANCARIA`
