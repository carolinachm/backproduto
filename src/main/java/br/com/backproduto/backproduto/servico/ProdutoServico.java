package br.com.backproduto.backproduto.servico;

import br.com.backproduto.backproduto.modelo.ProdutoModelo;
import br.com.backproduto.backproduto.repositorio.ProdutoRepositorio;
import br.com.backproduto.backproduto.util.Mensagem;

import java.io.IOException;
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

    // Metodo para listar todos os produtos
    public Iterable<ProdutoModelo> listarTodosProdutos() {
        return produtoRepositorio.findAll();
    }

    // Metodo para buscar um produto
    public Optional<ProdutoModelo> buscarProdutoPorCodigo(Long codigo) {
        return produtoRepositorio.findById(codigo);
    }

    // Metodo para cadastra e atualizar produtos
    public ResponseEntity<?> cadastrarProduto(ProdutoModelo produtoModelo, List<MultipartFile> imageFiles) {
       
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (MultipartFile imageFile : imageFiles) {
                try {
                    imageService.uploadImage(imageFile, savedProduct);
                } catch (IOException e) {
                    // Handle image upload error
                    e.printStackTrace();
                }
            }
        }else if (produtoModelo.getNome().isEmpty()) {
            mensagem.setMensagem("O campo nome é obrigatório!");
            return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (produtoModelo.getMarca().isEmpty()) {
            mensagem.setMensagem("O campo marca é obrigatório!");
            return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<ProdutoModelo>(produtoRepositorio.save(produtoModelo), HttpStatus.CREATED);
        }
    }

    public Optional<ProdutoModelo> getProductById(Long codigo) {
        return produtoRepositorio.findById(codigo);
    }


    // Metodo para editar produto
    public ResponseEntity<?> editarProduto(ProdutoModelo produtoModelo) {

        if (produtoRepositorio.countByCodigo(produtoModelo.getCodigo()) == 0) {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<Mensagem>(mensagem, HttpStatus.NOT_FOUND);
        } else if (produtoModelo.getNome().isEmpty()) {
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (produtoModelo.getMarca().isEmpty()) {
            mensagem.setMensagem("O campo marca é obrigatório!");
            return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<ProdutoModelo>(produtoRepositorio.save(produtoModelo), HttpStatus.OK);
        }
    }

    //Metodo para remover produto
    // Método para remover registros
    public ResponseEntity<?> removerProduto(Long codigo){

        if(produtoRepositorio.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{

            ProdutoModelo produtoModelo = produtoRepositorio.findByCodigo(codigo);
            produtoRepositorio.delete(produtoModelo);

            mensagem.setMensagem("Produto removida com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);

        }

    }


}
