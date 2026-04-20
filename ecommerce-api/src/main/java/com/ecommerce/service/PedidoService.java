package com.ecommerce.service;

import com.ecommerce.domain.dto.ItemPedidoDTO;
import com.ecommerce.domain.dto.PedidoDTO;
import com.ecommerce.domain.enums.StatusPedido;
import com.ecommerce.domain.model.ItemPedido;
import com.ecommerce.domain.model.Pedido;
import com.ecommerce.domain.model.Produto;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.exception.RegraDeNegocioException;
import com.ecommerce.repository.PedidoRepository;
import com.ecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteService clienteService;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido", id));
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> buscarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    @Transactional
    public Pedido criar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteService.buscarPorId(dto.getClienteId()));

        for (ItemPedidoDTO itemDTO : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", itemDTO.getProdutoId()));

            if (produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new RegraDeNegocioException(
                        "Estoque insuficiente para o produto: " + produto.getNome() +
                        ". Disponível: " + produto.getEstoque());
            }

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setValorUnitario(produto.getPreco());
            item.calcularSubtotal();
            item.setPedido(pedido);
            pedido.getItens().add(item);

            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id);
        validarTransicaoStatus(pedido.getStatus(), novoStatus);
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelar(Long id) {
        Pedido pedido = buscarPorId(id);
        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new RegraDeNegocioException("Pedido já entregue não pode ser cancelado");
        }
        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RegraDeNegocioException("Pedido já está cancelado");
        }

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }

    private void validarTransicaoStatus(StatusPedido atual, StatusPedido novo) {
        if (atual == StatusPedido.CANCELADO) {
            throw new RegraDeNegocioException("Pedido cancelado não pode mudar de status");
        }
        if (atual == StatusPedido.ENTREGUE) {
            throw new RegraDeNegocioException("Pedido já entregue não pode mudar de status");
        }
    }
}
