package com.restaurante.marmitas.mapper;

import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.entity.Produto;

public class ProdutoMapper {

    public static Produto mapToProduto(ProdutoRequestDto produtoDto, Produto produto) {
        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        return produto;
    }
	
}
