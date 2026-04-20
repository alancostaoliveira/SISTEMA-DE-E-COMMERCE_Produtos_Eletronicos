package com.ecommerce.controller;

import com.ecommerce.domain.dto.AvaliacaoDTO;
import com.ecommerce.domain.model.Avaliacao;
import com.ecommerce.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;

    @GetMapping("/produto/{produtoId}")
    public List<Avaliacao> buscarPorProduto(@PathVariable Long produtoId) {
        return service.buscarPorProduto(produtoId);
    }

    @GetMapping("/produto/{produtoId}/media")
    public ResponseEntity<Double> calcularMedia(@PathVariable Long produtoId) {
        return ResponseEntity.ok(service.calcularMedia(produtoId));
    }

    @PostMapping
    public ResponseEntity<Avaliacao> salvar(@Valid @RequestBody AvaliacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
