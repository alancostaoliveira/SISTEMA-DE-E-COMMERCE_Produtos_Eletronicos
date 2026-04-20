package com.ecommerce.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;
}
