package com.ecommerce.repository;

import com.ecommerce.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaId(Long categoriaId);

    List<Produto> findByMarcaId(Long marcaId);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByEstoqueGreaterThan(Integer estoque);
}
