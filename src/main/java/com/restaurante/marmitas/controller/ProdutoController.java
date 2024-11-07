package com.restaurante.marmitas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ResponseDto;
import com.restaurante.marmitas.service.IProdutoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/produtos")
@AllArgsConstructor
@Validated
public class ProdutoController {

	private IProdutoService iProdutoService;
	
	@PostMapping
	public ResponseEntity<ResponseDto> createProduto (@Valid @RequestBody ProdutoRequestDto produtoRequestDto) {
		iProdutoService.createProduto(produtoRequestDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(ProdutoConstants.STATUS_201, ProdutoConstants.MESSAGE_201));
	}
	
}
