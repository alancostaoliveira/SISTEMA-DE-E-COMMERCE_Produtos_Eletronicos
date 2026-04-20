package com.ecommerce.service;

import com.ecommerce.domain.dto.PagamentoDTO;
import com.ecommerce.domain.enums.StatusPagamento;
import com.ecommerce.domain.enums.StatusPedido;
import com.ecommerce.domain.model.Pagamento;
import com.ecommerce.domain.model.Pedido;
import com.ecommerce.exception.RegraDeNegocioException;
import com.ecommerce.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoService pedidoService;

    public List<Pagamento> listar() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new com.ecommerce.exception.RecursoNaoEncontradoException("Pagamento", id));
    }

    @Transactional
    public Pagamento processar(PagamentoDTO dto) {
        Pedido pedido = pedidoService.buscarPorId(dto.getPedidoId());

        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new RegraDeNegocioException("Pedido não está aguardando pagamento");
        }

        if (pagamentoRepository.findByPedidoId(pedido.getId()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um pagamento para este pedido");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setFormaPagamento(dto.getFormaPagamento());
        pagamento.setValor(pedido.getValorTotal());
        pagamento.setStatus(StatusPagamento.APROVADO);
        // NOTE: In a production environment, this should integrate with a payment gateway
        // (e.g., Stripe, PagSeguro, Mercado Pago) and set status based on the gateway's response.
        pagamento.setDataPagamento(LocalDateTime.now());

        pedidoService.atualizarStatus(pedido.getId(), StatusPedido.PAGO);

        return pagamentoRepository.save(pagamento);
    }
}
