package com.restaurante.marmitas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDto {
	
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 1, max = 50, message = "O nome deve ter entre 1 e 50 caracteres.")
    private String nome;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;
    
}