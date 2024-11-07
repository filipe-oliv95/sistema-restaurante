package com.restaurante.marmitas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.marmitas.constants.ProdutoConstants;
import com.restaurante.marmitas.dto.request.ProdutoRequestDto;
import com.restaurante.marmitas.service.IProdutoService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IProdutoService produtoService;

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
    void createProduto_deveRetornarErro400QuandoNomeEstaEmBranco() throws Exception {
        ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto();
        produtoRequestDto.setNome(""); // Nome em branco
        produtoRequestDto.setPreco(30.0);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value(ProdutoConstants.NOME_NOT_BLANK));
    }
    
    @Test @Transactional
    @Order(3)
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
    @Order(4)
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
    @Order(5)
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

}
