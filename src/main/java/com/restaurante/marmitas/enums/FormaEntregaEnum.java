package com.restaurante.marmitas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormaEntregaEnum {

    DELIVERY("Delivery"),
    RETIRADA("Retirada");

    private String descricao;
    
    @JsonCreator
    public static FormaEntregaEnum fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        for (FormaEntregaEnum formaEntrega : FormaEntregaEnum.values()) {
        	 if (formaEntrega.name().equalsIgnoreCase(value.trim())) {
                return formaEntrega;
            }
        }
        throw new IllegalArgumentException("Forma de entrega inválida: " + value);
    }   
}