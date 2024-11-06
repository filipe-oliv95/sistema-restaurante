package com.restaurante.marmitas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.marmitas.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>{

}
