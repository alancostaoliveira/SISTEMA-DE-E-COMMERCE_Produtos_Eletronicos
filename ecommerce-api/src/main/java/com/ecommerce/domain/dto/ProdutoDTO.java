package com.ecommerce.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    private Integer estoque;

    private Long categoriaId;
    private Long marcaId;
}
