package com.ecommerce.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AvaliacaoDTO {

    @NotNull(message = "Produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    private Integer nota;

    private String comentario;
}
