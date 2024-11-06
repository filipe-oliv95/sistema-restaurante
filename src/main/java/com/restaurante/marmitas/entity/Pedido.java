package com.restaurante.marmitas.entity;

import java.util.List;
import java.util.UUID;

import enums.FormaEntregaEnum;
import enums.FormaPagamentoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int ordemChegada;
    
    // Salvo como número ordinal no bd: 0 para DELIVERY e 1 para RETIRADA.
    @Enumerated(EnumType.ORDINAL)
    private FormaEntregaEnum formaEntrega;
    
    // Salvo como número ordinal no bd: 0 para PIX, 1 para DINHEIRO, 2 para CREDITO e 3 para DEBITO.
    @Enumerated(EnumType.ORDINAL)
    private FormaPagamentoEnum formaPagamento;
    
    private boolean confirmacaoEntrega = false;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
	private List<PedidoProduto> pedidos;
    
}
