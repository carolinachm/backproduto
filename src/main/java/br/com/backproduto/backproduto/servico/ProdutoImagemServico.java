package br.com.backproduto.backproduto.servico;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.backproduto.backproduto.modelo.ProdutoImagemModelo;
import br.com.backproduto.backproduto.repositorio.ProdutoImagemRepositorio;

@Service
public class ProdutoImagemServico {

    @Autowired
    private ProdutoImagemRepositorio produtoImagemRepositorio;

    public void uploadImagemProduto(MultipartFile file) throws IOException{
        ProdutoImagemModelo produtoImagemModelo = new ProdutoImagemModelo();

        produtoImagemModelo.setNome(file.getOriginalFilename());
        produtoImagemModelo.setType(file.getContentType());
        produtoImagemModelo.setData(file.getBytes());

        produtoImagemRepositorio.save(produtoImagemModelo);
    }

    public Optional<ProdutoImagemModelo> getProdutoImagemById(Long codigo){
        return produtoImagemRepositorio.findById(codigo);
    }
    
}
