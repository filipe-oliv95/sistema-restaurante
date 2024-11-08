package com.restaurante.marmitas.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ErrorResponseDto;
import com.restaurante.marmitas.dto.response.ResponseDto;
import com.restaurante.marmitas.service.IProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Produtos", description = "Gerenciamento de produtos, permitindo a criação, atualização, consulta e exclusão de informações de produtos no sistema.")
@ApiResponses({
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR (Erro interno do servidor)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))) })
@RestController
@RequestMapping(path = "/produtos")
@AllArgsConstructor
@Validated
public class ProdutoController {

	private IProdutoService iProdutoService;

	@Operation(summary = "Criar Produto", description = "Endpoint para criar um novo produto.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "CREATED (Criado com sucesso)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST (Solicitação Inválida)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "409", description = "CONFLICT (Conflito - Produto Já Existe)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping(produces = "application/json")
	public ResponseEntity<ResponseDto> createProduto(
			@Parameter @Valid @RequestBody ProdutoRequestDto produtoRequestDto) {
		iProdutoService.createProduto(produtoRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(ProdutoConstants.STATUS_201, ProdutoConstants.MESSAGE_201));
	}

	@Operation(summary = "Atualizar Produto", description = "Endpoint para atualizar um produto existente.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK (Atualizado com sucesso)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST (Solicitação Inválida)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "BAD REQUEST (Solicitação Inválida)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "409", description = "CONFLICT (Conflito - Produto Já Existe)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))), })
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ResponseDto> updateProduto(@Parameter @Valid @RequestBody ProdutoRequestDto produtoRequestDto,
			@Parameter(description = "UUID do produto", required = true) @PathVariable UUID id) {
		boolean isUpdated = iProdutoService.updateProduto(produtoRequestDto, id);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(ProdutoConstants.STATUS_200, ProdutoConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ProdutoConstants.STATUS_417, ProdutoConstants.MESSAGE_417_UPDATE));
		}
	}

}
