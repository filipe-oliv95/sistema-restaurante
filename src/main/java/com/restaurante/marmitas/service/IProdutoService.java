package com.restaurante.marmitas.service;

import java.util.List;
import java.util.UUID;

import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ProdutoResponseDto;

public interface IProdutoService {

    /**
    * Recebe nome e preço do produto
    * Verifica se o produto já existe no bd
    * Caso não exista, salva o produto no bd
    */
   void createProduto(ProdutoRequestDto produtoRequestDto);
   
   /**
    * Recebe novo nome e preço + id do produto
    * Verifica se o produto já existe no bd
    * Caso exista, atualiza o produto no bd
    */
   void updateProduto(ProdutoRequestDto produtoRequestDto, UUID id);
   
   /**
    * Retorna uma lista com os dados de todos os produtos
    */
   List<ProdutoResponseDto> fetchAllProdutos();
   
   /**
    * Retorna os dados de um produtos pelo id
    */
   ProdutoResponseDto fetchProduto(UUID id);
	
}
