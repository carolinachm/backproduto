package br.com.backproduto.backproduto.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.backproduto.backproduto.modelo.EnderecoModelo;

public interface EnderecoRepositorio extends CrudRepository<EnderecoModelo, Long>{
    
}
