package com.ecommerce.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
}
