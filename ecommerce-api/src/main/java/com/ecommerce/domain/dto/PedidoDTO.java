package com.ecommerce.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {

    private Long id;

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotEmpty(message = "O pedido deve ter pelo menos um item")
    private List<ItemPedidoDTO> itens;
}
