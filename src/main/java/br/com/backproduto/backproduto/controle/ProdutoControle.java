package br.com.backproduto.backproduto.controle;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.backproduto.backproduto.modelo.ProdutoModelo;
import br.com.backproduto.backproduto.servico.ProdutoServico;
import br.com.backproduto.backproduto.util.Mensagem;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ProdutoControle {

    @Autowired
    private ProdutoServico produtoServico;
    @Autowired
    private Mensagem mensagem;

   @GetMapping("/produto")
    public ResponseEntity<Iterable<ProdutoModelo>> listarTodosProdutos() {
        Iterable<ProdutoModelo> produtos = produtoServico.listarTodosProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoModelo> buscarProdutoPorId(@PathVariable Long codigo) {
        Optional<ProdutoModelo> produtoOptional = produtoServico.buscarProdutoPorCodigo(codigo);
        return new ResponseEntity<>(produtoOptional, HttpStatus.OK));
    }

    @PostMapping("/produto")
    public ResponseEntity<ProdutoModelo> cadastrarProduto(@RequestBody ProdutoModelo produtoModelo) {
        try {
            ProdutoModelo produtoModelo = produtoServico.cadastrarProduto(produtoModelo);
            return new ResponseEntity<>(produtoModelo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long codigo) {
        Optional<ProdutoModelo> existeProduto = produtoServico.buscarProdutoPorCodigo(codigo);
        if (existeProduto.isPresent()) {
            produtoServico.removerProduto(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    
}
