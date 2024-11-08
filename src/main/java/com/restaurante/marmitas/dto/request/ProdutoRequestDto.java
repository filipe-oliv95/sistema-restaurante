package com.restaurante.marmitas.dto.request;

import com.restaurante.marmitas.constants.ProdutoConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "ProdutoRequestDto",
        description = "Schema com informações do produto"
)
@Data
public class ProdutoRequestDto {
	
	@Schema(description = "Nome do produto: não pode estar em branco e deve conter entre 3 e 50 caracteres")
    @Size(min = 3, max = 50, message = ProdutoConstants.NOME_SIZE)
    private String nome;

	@Schema(description = "Preço do produto: deve ser um valor positivo")
    @NotNull(message = ProdutoConstants.PRECO_NOT_NULL)
    @Positive(message = ProdutoConstants.PRECO_POSITIVE)
    private Double preco;
    
}