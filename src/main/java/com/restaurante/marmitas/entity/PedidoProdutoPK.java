package com.restaurante.marmitas.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Embeddable
@Getter @Data
public class PedidoProdutoPK implements Serializable {
	private static final long serialVersionUID = 5943424773357086919L;

    private Long idProduto;
    private Long idPedido;

}
