package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;

@Service 
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService (ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Produto> cadastrarProduto(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();

        Optional<Restaurante> restauranteExists = restauranteRepository.findById(produtoRequest.getRestauranteId());
        if (restauranteExists.isEmpty()) {
            return Optional.empty();
        }

        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setRestaurante(restauranteExists.get());

        return Optional.of(produtoRepository.save(produto));
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizarProduto(Integer id, ProdutoRequest produtoRequest) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);

        if (produtoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Optional<Restaurante> restauranteExists = restauranteRepository.findById(produtoRequest.getRestauranteId());
        if (restauranteExists.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = produtoEncontrado.get();
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setRestaurante(restauranteExists.get());

        return Optional.of(produtoRepository.save(produto));
       }

       public boolean excluirProduto(Integer id) {
        if (!produtoRepository.existsById(id)) {
            return false;
        }
        
        produtoRepository.deleteById(id);
        return true;
    }
}

     
