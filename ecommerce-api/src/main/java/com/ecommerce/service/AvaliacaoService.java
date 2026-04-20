package com.ecommerce.service;

import com.ecommerce.domain.dto.AvaliacaoDTO;
import com.ecommerce.domain.model.Avaliacao;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;

    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    public List<Avaliacao> buscarPorProduto(Long produtoId) {
        return repository.findByProdutoId(produtoId);
    }

    public Double calcularMedia(Long produtoId) {
        Double media = repository.calcularMediaNotasPorProduto(produtoId);
        return media != null ? media : 0.0;
    }

    @Transactional
    public Avaliacao salvar(AvaliacaoDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setProduto(produtoService.buscarPorId(dto.getProdutoId()));
        avaliacao.setCliente(clienteService.buscarPorId(dto.getClienteId()));
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        return repository.save(avaliacao);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Avaliação", id);
        }
        repository.deleteById(id);
    }
}
