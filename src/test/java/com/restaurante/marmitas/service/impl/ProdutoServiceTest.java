package com.restaurante.marmitas.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.entity.Produto;
import com.restaurante.marmitas.exception.ProductAlreadyExistsException;
import com.restaurante.marmitas.repository.ProdutoRepository;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduto_deveSalvarProdutoQuandoNaoExistente() {
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Teste");
        produtoRequestDto.setPreco(25.0);

        when(produtoRepository.findByNome(produtoRequestDto.getNome())).thenReturn(Optional.empty());

        produtoService.createProduto(produtoRequestDto);

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void createProduto_deveLancarExcecaoQuandoProdutoJaExistir() {
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Duplicado");
        produtoRequestDto.setPreco(30.0);

        Produto produtoExistente = new Produto();
        produtoExistente.setNome(produtoRequestDto.getNome());
        produtoExistente.setPreco(25.0);
        
        when(produtoRepository.findByNome(produtoRequestDto.getNome())).thenReturn(Optional.of(produtoExistente));

        assertThatThrownBy(() -> produtoService.createProduto(produtoRequestDto))
            .isInstanceOf(ProductAlreadyExistsException.class)
            .hasMessageContaining(ProdutoConstants.NOME_ALREADY_EXIST + produtoExistente.getNome());

        verify(produtoRepository, never()).save(any(Produto.class));
    }

}