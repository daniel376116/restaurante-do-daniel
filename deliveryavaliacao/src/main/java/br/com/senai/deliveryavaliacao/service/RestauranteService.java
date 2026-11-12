package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.dto.RestauranteRequest;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service 
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Restaurante> cadastrar(RestauranteRequest restauranteRequest) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteRequest.getNome());
        restaurante.setEndereco(restauranteRequest.getEndereco());

        return Optional.of(restauranteRepository.save(restaurante));
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarPorId(Integer id) {
        return restauranteRepository.findById(id);
    }

    public Optional<Restaurante> atualizar(Integer id, RestauranteRequest restauranteAtualizado) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);

        if (restauranteExistente.isEmpty()) {
            return Optional.empty();
        }

        Restaurante restaurante = restauranteExistente.get();
        restaurante.setNome(restauranteAtualizado.getNome());
        restaurante.setEndereco(restauranteAtualizado.getEndereco());
        
        return Optional.of(restauranteRepository.save(restaurante));
    }

    public boolean deletar(Integer id) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);

        if (restauranteExistente.isEmpty()) {
            return false;
        }

        restauranteRepository.deleteById(id);
        return true;
    }

}
