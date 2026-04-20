package com.ecommerce.service;

import com.ecommerce.domain.dto.ClienteDTO;
import com.ecommerce.domain.model.Cliente;
import com.ecommerce.exception.RecursoNaoEncontradoException;
import com.ecommerce.exception.RegraDeNegocioException;
import com.ecommerce.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", id));
    }

    @Transactional
    public Cliente salvar(ClienteDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RegraDeNegocioException("Já existe um cliente com o email: " + dto.getEmail());
        }
        if (repository.existsByCpf(dto.getCpf())) {
            throw new RegraDeNegocioException("Já existe um cliente com o CPF: " + dto.getCpf());
        }
        Cliente cliente = new Cliente();
        preencherCliente(cliente, dto);
        return repository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = buscarPorId(id);
        if (!cliente.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new RegraDeNegocioException("Já existe um cliente com o email: " + dto.getEmail());
        }
        if (!cliente.getCpf().equals(dto.getCpf()) && repository.existsByCpf(dto.getCpf())) {
            throw new RegraDeNegocioException("Já existe um cliente com o CPF: " + dto.getCpf());
        }
        preencherCliente(cliente, dto);
        return repository.save(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    private void preencherCliente(Cliente cliente, ClienteDTO dto) {
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCidade(dto.getCidade());
        cliente.setEstado(dto.getEstado());
        cliente.setCep(dto.getCep());
    }
}
