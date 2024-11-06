package com.restaurante.marmitas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PedidoProduto extends BaseEntity{

	@EmbeddedId
	private PedidoProdutoPK pedidoProdutoPK;

	@ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    
    @Column(name="quantidade")
    private Integer quantidade;

}
