package br.com.backproduto.backproduto.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.backproduto.backproduto.modelo.CategoriaModelo;

public interface CategoriaRepositorio extends CrudRepository<CategoriaModelo, Long>{
    
}
