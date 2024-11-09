package com.restaurante.marmitas.mapper;

import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ProdutoResponseDto;
import com.restaurante.marmitas.entity.Produto;

public class ProdutoMapper {

	public static Produto mapToProduto(ProdutoRequestDto produtoDto, Produto produto) {
		produto.setNome(produtoDto.getNome());
		produto.setPreco(produtoDto.getPreco());
		return produto;
	}

	public static ProdutoResponseDto mapToProdutoResponseDto(Produto produto) {
		ProdutoResponseDto produtoDto = new ProdutoResponseDto(produto.getId(), produto.getNome(), produto.getPreco(),
				produto.isDisponivel(), produto.getCreatedAt(), produto.getUpdatedAt());
		return produtoDto;
	}

}
