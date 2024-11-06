package com.restaurante.marmitas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.marmitas.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

}
