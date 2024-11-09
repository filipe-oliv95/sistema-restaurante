package com.restaurante.marmitas.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.dto.response.ProdutoResponseDto;
import com.restaurante.marmitas.entity.Produto;
import com.restaurante.marmitas.service.IProdutoService;
import com.restaurante.marmitas.utils.TestUtils;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProdutoService iProdutoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test @Transactional
    @Order(1)
    void createProduto_deveRetornarStatus201QuandoProdutoCriadoComSucesso() throws Exception {
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Teste");
        produtoRequestDto.setPreco(30.0);
        
        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(produtoRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(ProdutoConstants.STATUS_201))
                .andExpect(jsonPath("$.statusMsg").value(ProdutoConstants.MESSAGE_201));
    }

    @Test @Transactional
    @Order(2)
    void createProduto_deveRetornarErro400QuandoNomeTiverMaisQue50Caracteres() throws Exception {
    	ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
    	produtoRequestDto.setNome("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqwe"); // Nome > 50
    	produtoRequestDto.setPreco(30.0);
    	
    	mockMvc.perform(post("/produtos")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(produtoRequestDto)))
    	.andExpect(status().isBadRequest())
    	.andExpect(jsonPath("$.nome").value(ProdutoConstants.NOME_SIZE));
    }
    
    @Test @Transactional
    @Order(3)
    void createProduto_deveRetornarErro400QuandoNomeTiverMenosQue3Caracteres() throws Exception {
    	ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
    	produtoRequestDto.setNome("Aa"); // Nome < 3
    	produtoRequestDto.setPreco(30.0);
    	
    	mockMvc.perform(post("/produtos")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(produtoRequestDto)))
    	.andExpect(status().isBadRequest())
    	.andExpect(jsonPath("$.nome").value(ProdutoConstants.NOME_SIZE));
    }

    @Test @Transactional
    @Order(4)
    void createProduto_deveRetornarErro400QuandoPrecoForNegativo() throws Exception {
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Teste");
        produtoRequestDto.setPreco(-10.0); // Preço negativo

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.preco").value(ProdutoConstants.PRECO_POSITIVE));
    }
    
    @Test @Transactional
    @Order(5)
    void updateProduto_deveRetornarStatus200QuandoProdutoForAtualizadoComSucesso() throws Exception {
        // Produto existente
    	Produto produtoExistente = TestUtils.criarProdutoExistente("Produto Existente", 20.0);
    	UUID produtoId = produtoExistente.getId();
    	
    	// Dados recebidos para atualização
    	ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome("Produto Teste");
        produtoRequestDto.setPreco(30.0);
        
        // Executa o PUT e valida a resposta
        mockMvc.perform(put("/produtos/{id}", produtoId)
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(produtoRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(ProdutoConstants.STATUS_200))
                .andExpect(jsonPath("$.statusMsg").value(ProdutoConstants.MESSAGE_200));
    }
    
    @Test @Transactional
    @Order(6)
    void updateProduto_deveRetornarStatus400QuandoNomeTiverMaisQue50Caracteres() throws Exception {
    	// Produto existente
    	Produto produtoExistente = TestUtils.criarProdutoExistente("Produto Existente", 20.0);
    	UUID produtoId = produtoExistente.getId();
    	
    	// Dados recebidos para atualização
    	ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
    	produtoRequestDto.setNome("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqwe"); // Nome > 50
    	produtoRequestDto.setPreco(30.0);
    	
    	// Executa o PUT e valida a resposta
    	mockMvc.perform(put("/produtos/{id}", produtoId)
    			.contentType(MediaType.APPLICATION_JSON) 
    			.content(objectMapper.writeValueAsString(produtoRequestDto)))
    	.andExpect(status().isBadRequest())
    	.andExpect(jsonPath("$.nome").value(ProdutoConstants.NOME_SIZE));
    }
    
    @Test @Transactional
    @Order(7)
    void updateProduto_deveRetornarErro400QuandoNomeTiverMenosQue3Caracteres() throws Exception {
    	// Produto existente
    	Produto produtoExistente = TestUtils.criarProdutoExistente("Produto Existente", 20.0);
    	UUID produtoId = produtoExistente.getId();
    	
    	// Dados recebidos para atualização
    	ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
    	produtoRequestDto.setNome("Aa"); // Nome < 3
    	produtoRequestDto.setPreco(30.0);
    	
    	// Executa o PUT e valida a resposta
    	mockMvc.perform(put("/produtos/{id}", produtoId)
    			.contentType(MediaType.APPLICATION_JSON) 
    			.content(objectMapper.writeValueAsString(produtoRequestDto)))
    	.andExpect(status().isBadRequest())
    	.andExpect(jsonPath("$.nome").value(ProdutoConstants.NOME_SIZE));
    }
    
    @Test @Transactional
    @Order(8)
    void updateProduto_deveRetornarErro400QuandoPrecoForNegativo() throws Exception {
    	// Produto existente
    	Produto produtoExistente = TestUtils.criarProdutoExistente("Produto Existente", 20.0);
    	UUID produtoId = produtoExistente.getId();
    	
    	// Dados recebidos para atualização
    	ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
    	produtoRequestDto.setNome("Produto Teste");
    	produtoRequestDto.setPreco(-30.0); // Valor negativo
    	
    	// Executa o PUT e valida a resposta
    	mockMvc.perform(put("/produtos/{id}", produtoId)
    			.contentType(MediaType.APPLICATION_JSON) 
    			.content(objectMapper.writeValueAsString(produtoRequestDto)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.preco").value(ProdutoConstants.PRECO_POSITIVE));
    }
    
    @Test @Transactional
    @Order(9)
    void fetchAllProdutos_deveRetornarTodosOsProdutos() throws Exception {
        ProdutoResponseDto produto1 = TestUtils.criarProdutoResponseDto("Produto A", 21.5);
        ProdutoResponseDto produto2 = TestUtils.criarProdutoResponseDto("Produto B", 15.0);
        List<ProdutoResponseDto> produtos = Arrays.asList(produto1, produto2);

        when(iProdutoService.fetchAllProdutos()).thenReturn(produtos);

        mockMvc.perform(get("/produtos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto A"))
                .andExpect(jsonPath("$[1].nome").value("Produto B"));
    }
}
