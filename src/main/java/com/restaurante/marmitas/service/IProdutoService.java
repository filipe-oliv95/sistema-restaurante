package com.restaurante.marmitas.service;

import com.restaurante.marmitas.dto.request.ProdutoRequestDto;

public interface IProdutoService {

    /**
    * Recebe nome e preço do produto
    * Verifica se o produto já existe no bd
    * Caso não exista, salva o produto no bd
    */
   void createProduto(ProdutoRequestDto produtoRequestDto);
	
}
