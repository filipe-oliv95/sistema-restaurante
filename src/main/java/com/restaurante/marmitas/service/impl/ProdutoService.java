package com.restaurante.marmitas.service.impl;

import org.springframework.stereotype.Service;

import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.entity.Produto;
import com.restaurante.marmitas.exception.ProductAlreadyExistsException;
import com.restaurante.marmitas.mapper.ProdutoMapper;
import com.restaurante.marmitas.repository.ProdutoRepository;
import com.restaurante.marmitas.service.IProdutoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService implements IProdutoService{

	private ProdutoRepository produtoRepository;
	
	@Override
	public void createProduto(ProdutoRequestDto produtoRequestDto) {
		Produto produto = ProdutoMapper.mapToProduto(produtoRequestDto, new Produto());
		// verificar se o produto ja existe pelo nome
		produtoRepository.findByNome(produtoRequestDto.getNome())
				.ifPresent(produtoExistente -> {
		            throw new ProductAlreadyExistsException("Produto já existente com o nome: " + produtoRequestDto.getNome());
		        });
		produtoRepository.save(produto);
	}

}
