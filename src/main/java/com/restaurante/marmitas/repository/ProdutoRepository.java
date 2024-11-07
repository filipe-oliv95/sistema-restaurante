package com.restaurante.marmitas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.marmitas.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

	Optional<Produto> findByNome(String nome);

}
