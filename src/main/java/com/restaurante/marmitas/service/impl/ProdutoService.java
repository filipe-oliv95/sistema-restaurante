package com.restaurante.marmitas.service.impl;

import org.springframework.stereotype.Service;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.entity.Produto;
import com.restaurante.marmitas.exception.ProductAlreadyExistsException;
import com.restaurante.marmitas.mapper.ProdutoMapper;
import com.restaurante.marmitas.repository.ProdutoRepository;
import com.restaurante.marmitas.service.IProdutoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProdutoService implements IProdutoService{

	private ProdutoRepository produtoRepository;
	
	@Override
	public void createProduto(ProdutoRequestDto produtoRequestDto) {
		log.debug("Iniciando criação do produto com dados: {}", produtoRequestDto);
		Produto produto = ProdutoMapper.mapToProduto(produtoRequestDto, new Produto());
		produtoRepository.findByNome(produtoRequestDto.getNome())
				.ifPresent(produtoExistente -> {
					log.warn("Produto já existe com o nome: {}", produtoRequestDto.getNome());
		            throw new ProductAlreadyExistsException(ProdutoConstants.NOME_ALREADY_EXIST + produtoRequestDto.getNome());
		        });
		produtoRepository.save(produto);
		log.info("Produto salvo com sucesso: {}", produto.getNome());
	}

}
