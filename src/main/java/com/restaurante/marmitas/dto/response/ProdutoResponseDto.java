package com.restaurante.marmitas.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ProdutoResponseDto",
        description = "Schema com informações do produto"
)
public record ProdutoResponseDto (
	    
	    @Schema(description = "Identificador único do produto")
	    UUID id,
	    
	    @Schema(description = "Nome do produto")
	    String nome,
	    
	    @Schema(description = "Preço do produto")
	    Double preco,
	    
	    @Schema(description = "Disponibilidade do produto")
	    boolean disponivel,
	    
	    @Schema(description = "Data de criação do produto")
	    LocalDateTime createdAt,
	    
	    @Schema(description = "Data de atualização do produto")
	    LocalDateTime updatedAt
	) {}