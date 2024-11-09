package com.restaurante.marmitas.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ProdutoResponseDto;
import com.restaurante.marmitas.entity.Produto;


public class TestUtils {

	// Método para criar um produto com nome e preço especificados
	public static Produto criarProdutoExistente(String nome, double preco) {
		Produto produto = new Produto();
		produto.setId(UUID.randomUUID());
		produto.setNome(nome);
		produto.setPreco(preco);
		return produto;
	}
	
	// Sobrecarga: Método para criar um produto com nome, preço e datas especificadas
	public static Produto criarProdutoExistente(String nome, double preco, LocalDateTime createdAt, LocalDateTime updatedAt) {
		Produto produto = new Produto();
		produto.setId(UUID.randomUUID());
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setCreatedAt(createdAt);
		produto.setUpdatedAt(updatedAt);
		return produto;
	}
	
	// Método para criar um dto request de produto
	public static ProdutoRequestDto criarProdutoRequestDto(String nome, double preco) {
		ProdutoRequestDto produtoDto = new ProdutoRequestDto(nome, preco);
		return produtoDto;
	}
	
	// Método para criar um dto response de produto
	public static ProdutoResponseDto criarProdutoResponseDto(String nome, double preco) {
		ProdutoResponseDto produtoDto = new ProdutoResponseDto(UUID.randomUUID(), nome, preco, true, LocalDateTime.now(), null);
		return produtoDto;
	}

}