package com.ecommerce.service;

import com.ecommerce.domain.dto.CategoriaDTO;
import com.ecommerce.domain.model.Categoria;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.exception.RegraDeNegocioException;
import com.ecommerce.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", id));
    }

    @Transactional
    public Categoria salvar(CategoriaDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new RegraDeNegocioException("Já existe uma categoria com o nome: " + dto.getNome());
        }
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return repository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = buscarPorId(id);
        if (!categoria.getNome().equals(dto.getNome()) && repository.existsByNome(dto.getNome())) {
            throw new RegraDeNegocioException("Já existe uma categoria com o nome: " + dto.getNome());
        }
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return repository.save(categoria);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
