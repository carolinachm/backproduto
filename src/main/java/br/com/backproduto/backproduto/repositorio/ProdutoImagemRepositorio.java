package br.com.backproduto.backproduto.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.backproduto.backproduto.modelo.ProdutoImagemModelo;

public interface ProdutoImagemRepositorio extends CrudRepository<ProdutoImagemModelo, Long>{
    
}
