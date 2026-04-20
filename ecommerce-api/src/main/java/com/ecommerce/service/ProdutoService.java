package com.ecommerce.service;

import com.ecommerce.domain.dto.ProdutoDTO;
import com.ecommerce.domain.model.Produto;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.exception.RegraDeNegocioException;
import com.ecommerce.repository.CategoriaRepository;
import com.ecommerce.repository.MarcaRepository;
import com.ecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", id));
    }

    public List<Produto> buscarPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }

    public List<Produto> buscarPorMarca(Long marcaId) {
        return repository.findByMarcaId(marcaId);
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> listarEmEstoque() {
        return repository.findByEstoqueGreaterThan(0);
    }

    @Transactional
    public Produto salvar(ProdutoDTO dto) {
        Produto produto = new Produto();
        preencherProduto(produto, dto);
        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produto = buscarPorId(id);
        preencherProduto(produto, dto);
        return repository.save(produto);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    @Transactional
    public Produto atualizarEstoque(Long id, Integer quantidade) {
        Produto produto = buscarPorId(id);
        int novoEstoque = produto.getEstoque() + quantidade;
        if (novoEstoque < 0) {
            throw new RegraDeNegocioException("Estoque insuficiente para o produto: " + produto.getNome());
        }
        produto.setEstoque(novoEstoque);
        return repository.save(produto);
    }

    private void preencherProduto(Produto produto, ProdutoDTO dto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());

        if (dto.getCategoriaId() != null) {
            produto.setCategoria(categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", dto.getCategoriaId())));
        }

        if (dto.getMarcaId() != null) {
            produto.setMarca(marcaRepository.findById(dto.getMarcaId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Marca", dto.getMarcaId())));
        }
    }
}
