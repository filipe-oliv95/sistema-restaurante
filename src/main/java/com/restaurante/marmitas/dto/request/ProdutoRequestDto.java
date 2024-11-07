package com.restaurante.marmitas.dto.request;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.interfaces.NotBlankGroup;
import com.restaurante.marmitas.interfaces.NotNullGroup;
import com.restaurante.marmitas.interfaces.PositiveNumberGroup;
import com.restaurante.marmitas.interfaces.SizeGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDto {
	
    @NotBlank(message = ProdutoConstants.NOME_NOT_BLANK, groups = NotBlankGroup.class)
    @Size(min = 3, max = 50, message = ProdutoConstants.NOME_SIZE, groups = SizeGroup.class)
    private String nome;

    @NotNull(message = ProdutoConstants.PRECO_NOT_NULL, groups = NotNullGroup.class)
    @Positive(message = ProdutoConstants.PRECO_POSITIVE, groups = PositiveNumberGroup.class)
    private Double preco;
    
}