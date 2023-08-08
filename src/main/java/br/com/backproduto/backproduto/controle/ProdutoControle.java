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

    @PostMapping("/produto")
    public ResponseEntity<?> cadastraProduto(@RequestBody ProdutoModelo produtoModelo,
                                                 @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles) {
        ProdutoModelo cadastraProduto = produtoServico.cadastrarProduto(produtoModelo, imageFiles);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/produto")
    public Iterable listarTodosProdutos(){
        return produtoServico.listarTodosProdutos();
    }
    
    @GetMapping("/produto/{codigo}")
    public ResponseEntity<?> buscarProdutoPorCodigo(@PathVariable Long codigo) {
        Optional<ProdutoModelo> produtoModeloOptional = produtoServico.buscarProdutoPorCodigo(codigo);

        if (!produtoModeloOptional.isPresent()) {
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("Código do produto não existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }

        return ResponseEntity.ok(produtoModeloOptional.get());
    }

    @PutMapping("/produto")
    public ResponseEntity<?> editarProduto(@RequestBody ProdutoModelo produtoModelo){
        return produtoServico.editarProduto(produtoModelo);
    }
    @DeleteMapping("/produto/{codigo}")
    public ResponseEntity<?> removerProduto(@PathVariable Long codigo) {
        return produtoServico.removerProduto(codigo);
    }



    
}
