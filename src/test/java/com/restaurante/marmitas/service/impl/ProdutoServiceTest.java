package com.restaurante.marmitas.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.entity.Produto;
import com.restaurante.marmitas.exception.ProductAlreadyExistsException;
import com.restaurante.marmitas.exception.ResourceNotFoundException;
import com.restaurante.marmitas.repository.ProdutoRepository;
import com.restaurante.marmitas.utils.TestUtils;

import jakarta.transaction.Transactional;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test @Transactional
    @Order(1)
    void createProduto_deveSalvarProdutoQuandoNaoExistente() {
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Teste");
        produtoRequestDto.setPreco(25.0);

        when(produtoRepository.findByNome(produtoRequestDto.getNome())).thenReturn(Optional.empty());

        produtoService.createProduto(produtoRequestDto);

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test @Transactional
    @Order(2)
    void createProduto_deveRetornarStatus409QuandoProdutoJaExistir() {
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
    
    @Test @Transactional
    @Order(3)
    void updateProduto_deveAtualizarProdutoQuandoExistente() {
        // Produto existente no repositório
        Produto produtoExistente = TestUtils.criarProdutoExistente("Produto Original", 20.0);

        when(produtoRepository.findById(produtoExistente.getId())).thenReturn(Optional.of(produtoExistente));

        // Dados para a atualização
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Atualizado");
        produtoRequestDto.setPreco(30.0);

        // Executa a atualização
        produtoService.updateProduto(produtoRequestDto, produtoExistente.getId());

        // Verifica se o repositório foi chamado com o produto atualizado
        verify(produtoRepository, times(1)).save(produtoExistente);

        // Valida que o produto existente foi atualizado corretamente
        assertThat(produtoExistente.getNome()).isEqualTo("Produto Atualizado");
        assertThat(produtoExistente.getPreco()).isEqualTo(30.0);
    }
    
    @Test @Transactional
    @Order(4)
    void updateProduto_deveRetornar409QuandoProdutoJaExistirComNomeEscolhido() {
        // Produtos existentes no repositório
        // Teste atualizando o produto 1 com o nome do produto 2
        Produto produtoExistenteUm = TestUtils.criarProdutoExistente("Produto Existente 1", 20.0);
        Produto produtoExistenteDois = TestUtils.criarProdutoExistente("Produto Existente 2", 30.0);
        
        // Dados para a atualização
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Existente 2"); // Nome já existente
        produtoRequestDto.setPreco(40.0);
        
        // Simula o produto 1 sendo encontrado
        when(produtoRepository.findById(produtoExistenteUm.getId())).thenReturn(Optional.of(produtoExistenteUm));

        // Simula produto 2 no repositório com o mesmo nome solicitado na atualização
        when(produtoRepository.findByNome(produtoRequestDto.getNome())).thenReturn(Optional.of(produtoExistenteDois));

        // Executa a atualização e verifica que a exceção esperada é lançada com a mensagem correta
        assertThatThrownBy(() -> produtoService.updateProduto(produtoRequestDto, produtoExistenteUm.getId()))
            .isInstanceOf(ProductAlreadyExistsException.class)
            .hasMessageContaining(ProdutoConstants.NOME_ALREADY_EXIST + produtoRequestDto.getNome());

        // Verifica que o repositório não tentou salvar o produto, pois a atualização falhou
        verify(produtoRepository, never()).save(any(Produto.class));
    }
    
    @Test @Transactional
    @Order(5)
    void updateProduto_deveRetornarStatus404QuandoProdutoNaoForEncontrado() {
        // Dados para a atualização
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Atualizado");
        produtoRequestDto.setPreco(30.0);
        UUID id = UUID.randomUUID();

        // Simula a ausência do produto no repositório
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> produtoService.updateProduto(produtoRequestDto, id))
	        .isInstanceOf(ResourceNotFoundException.class)
	        .hasMessageContaining(ProdutoConstants.MESSAGE_404 + id);

        // Verifica que o repositório não tentou salvar o produto
        verify(produtoRepository, never()).save(any(Produto.class));
    }

}