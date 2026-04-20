package com.ecommerce.domain.dto;

import com.ecommerce.domain.enums.FormaPagamento;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PagamentoDTO {

    @NotNull(message = "Pedido é obrigatório")
    private Long pedidoId;

    @NotNull(message = "Forma de pagamento é obrigatória")
    private FormaPagamento formaPagamento;
}
