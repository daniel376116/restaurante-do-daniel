package br.com.senai.deliveryavaliacao.dto;

import jakarta.validation.constraints.NotBlank;

public class RestauranteRequest {
    @NotBlank (message = "O nome do restaurante é obrigatório")
    private String nome;

    @NotBlank (message = "O endereço do restaurante é obrigatório")
    private String endereco;

    public RestauranteRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }   
}
