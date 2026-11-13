package br.com.senai.deliveryavaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Optional<Produto>> cadastrarProduto(@Valid @RequestBody ProdutoRequest produtoRequest) {
        Optional<Produto> produtoCadastrado = produtoService.cadastrarProduto(produtoRequest);

        if (produtoCadastrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Produto>> buscarProdutoPorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto);
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoRequest produtoRequest) {
        Optional<Produto> produtoAtual = produtoService.atualizarProduto(id, produtoRequest);

        if (produtoAtual.isPresent()) {
            return ResponseEntity.ok(produtoAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Integer id) {
        boolean excluido = produtoService.excluirProduto(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
