package br.com.backproduto.backproduto.servico;

import br.com.backproduto.backproduto.modelo.ProdutoModelo;
import br.com.backproduto.backproduto.repositorio.ProdutoRepositorio;
import br.com.backproduto.backproduto.util.Mensagem;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @Autowired
    private Mensagem mensagem;

    //Metodo para cadastrar produtos
    public ProdutoModelo cadastrarProduto(ProdutoModelo produtoModelo){
        //TODO: fazer validação e implementar o Produto Imagem
        return produtoRepositorio.save(produtoModelo);
    }

    // Metodo para listar todos os produtos
    public Iterable<ProdutoModelo> listarTodosProdutos() {
        return produtoRepositorio.findAll();
    }

    // Metodo para buscar um produto
    public Optional<ProdutoModelo> buscarProdutoPorCodigo(Long codigo) {
        return produtoRepositorio.findById(codigo);
    }

   
    //Metodo para remover produto
    // Método para remover registros
    public void removerProduto(Long codigo){
        produtoRepositorio.deleteById(codigo);

    }


}
