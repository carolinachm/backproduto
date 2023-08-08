package br.com.backproduto.backproduto.repositorio;

import br.com.backproduto.backproduto.modelo.ProdutoModelo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface ProdutoRepositorio extends CrudRepository <ProdutoModelo, Long> {

    ProdutoModelo findByCodigo(Long codigo);

    Long countByCodigo(Long codigo);

}
