package com.restaurante.marmitas.dto.request;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.interfaces.NotBlankGroup;
import com.restaurante.marmitas.interfaces.NotNullGroup;
import com.restaurante.marmitas.interfaces.PositiveNumberGroup;
import com.restaurante.marmitas.interfaces.SizeGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "ProdutoRequestDto",
        description = "Schema com informações para criar um novo produto"
)
@Data
public class ProdutoRequestDto {
	
	@Schema(description = "Nome do novo produto: não pode estar em branco e deve conter entre 3 e 50 caracteres")
    @NotBlank(message = ProdutoConstants.NOME_NOT_BLANK, groups = NotBlankGroup.class)
    @Size(min = 3, max = 50, message = ProdutoConstants.NOME_SIZE, groups = SizeGroup.class)
    private String nome;

	@Schema(description = "Preço do novo produto: deve ser um valor positivo")
    @NotNull(message = ProdutoConstants.PRECO_NOT_NULL, groups = NotNullGroup.class)
    @Positive(message = ProdutoConstants.PRECO_POSITIVE, groups = PositiveNumberGroup.class)
    private Double preco;
    
}