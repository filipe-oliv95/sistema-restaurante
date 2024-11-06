package com.restaurante.marmitas.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Pedido extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="ordem_chegada")
    private Integer ordemChegada;
    
    @Column(name="forma_entrega")
    private String formaEntrega;
    
    @Column(name="forma_pagamento")
    private String ruaformaPagamento;
    
    @Column(name="confirmacao_entrega")
    private Boolean confirmacaoEntrega;

	@ManyToOne
	@JoinColumn(name = "id_cliente_FK", referencedColumnName = "id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
	private List<PedidoProduto> pedidos;
    
}
