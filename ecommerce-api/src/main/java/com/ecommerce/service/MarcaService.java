package com.ecommerce.service;

import com.ecommerce.domain.dto.MarcaDTO;
import com.ecommerce.domain.model.Marca;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.exception.RegraDeNegocioException;
import com.ecommerce.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository repository;

    public List<Marca> listar() {
        return repository.findAll();
    }

    public Marca buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Marca", id));
    }

    @Transactional
    public Marca salvar(MarcaDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new RegraDeNegocioException("Já existe uma marca com o nome: " + dto.getNome());
        }
        Marca marca = new Marca();
        marca.setNome(dto.getNome());
        marca.setDescricao(dto.getDescricao());
        marca.setPaisOrigem(dto.getPaisOrigem());
        return repository.save(marca);
    }

    @Transactional
    public Marca atualizar(Long id, MarcaDTO dto) {
        Marca marca = buscarPorId(id);
        if (!marca.getNome().equals(dto.getNome()) && repository.existsByNome(dto.getNome())) {
            throw new RegraDeNegocioException("Já existe uma marca com o nome: " + dto.getNome());
        }
        marca.setNome(dto.getNome());
        marca.setDescricao(dto.getDescricao());
        marca.setPaisOrigem(dto.getPaisOrigem());
        return repository.save(marca);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
