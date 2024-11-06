package com.restaurante.marmitas.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Cliente extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="aniversario")
    private LocalDate aniversario;
    
    @Column(name="whatsapp")
    private String whatsapp;
    
    @Column(name="rua")
    private String rua;
    
    @Column(name="numero")
    private String numero;
    
    @Column(name="bairro")
    private String bairro;
    
    @Column(name="complemento")
    private String complemento;
    
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Pedido> pedidos;
    
}
