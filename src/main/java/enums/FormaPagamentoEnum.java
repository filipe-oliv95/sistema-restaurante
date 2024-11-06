package enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormaPagamentoEnum {

    PIX("Pix"),
    DINHEIRO("Dinheiro"),
    CREDITO("Credito"),
    DEBITO("Debito");

    private String descricao;
    
    @JsonCreator
    public static FormaPagamentoEnum fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        for (FormaPagamentoEnum formaPagamento : FormaPagamentoEnum.values()) {
        	 if (formaPagamento.name().equalsIgnoreCase(value.trim())) {
                return formaPagamento;
            }
        }
        throw new IllegalArgumentException("Forma de pagamento inválida: " + value);
    }   
}