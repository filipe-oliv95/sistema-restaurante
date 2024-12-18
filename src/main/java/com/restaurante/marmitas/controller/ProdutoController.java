package com.restaurante.marmitas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ErrorResponseDto;
import com.restaurante.marmitas.dto.response.ProdutoResponseDto;
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
			@ApiResponse(responseCode = "409", description = "CONFLICT (Produto já Existe)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))) })
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
			@ApiResponse(responseCode = "400", description = "BAD REQUEST (Solicitação inválida)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "NOT FOUND (Produto não encontrado)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "409", description = "CONFLICT (Produto com nome já existente)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))), })
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ResponseDto> updateProduto(@Parameter @Valid @RequestBody ProdutoRequestDto produtoRequestDto,
			@Parameter(description = "UUID do produto", required = true) @PathVariable UUID id) {
		iProdutoService.updateProduto(produtoRequestDto, id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(ProdutoConstants.STATUS_200, ProdutoConstants.MESSAGE_200));
	}

	@Operation(summary = "Retorna todos os produtos", description = "Endpoint para retornar informações de todos os produtos.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK (Produtos retornados com sucesso)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponseDto.class))) })
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDto>> fetchAllProdutos() {
		return ResponseEntity.status(HttpStatus.OK).body(iProdutoService.fetchAllProdutos());
	}

	@Operation(summary = "Retorna um produto pelo id", description = "Endpoint para retornar informações de um produto pelo id.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK (Produto retornado com sucesso)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "NOT FOUND (Produto não encontrado)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoResponseDto> fetchProduto(@Parameter(description = "UUID do produto", required = true) @PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(iProdutoService.fetchProduto(id));
	}

}
