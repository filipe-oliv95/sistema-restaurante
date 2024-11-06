package com.restaurante.marmitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.marmitas.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
