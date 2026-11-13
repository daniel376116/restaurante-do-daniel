package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequest {
    
    @NotBlank (message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull (message = "Informe o preço do produto")
    private BigDecimal preco;

    @NotNull (message = "Informe o id do restaurante")
    private Integer restauranteId;

    public ProdutoRequest () {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return  preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getRestauranteId() {
        return  restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }
}
