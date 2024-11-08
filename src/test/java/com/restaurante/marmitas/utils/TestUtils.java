package com.restaurante.marmitas.utils;

import java.util.UUID;

import com.restaurante.marmitas.entity.Produto;


public class TestUtils {

    // Método para criar um produto com nome e preço especificados
    public static Produto criarProdutoExistente(String nome, double preco) {
        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setNome(nome);
        produto.setPreco(preco);
        return produto;
    }

}