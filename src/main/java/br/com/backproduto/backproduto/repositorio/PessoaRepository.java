package br.com.backproduto.backproduto.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.backproduto.backproduto.modelo.PessoaModelo;

public interface PessoaRepository extends CrudRepository<PessoaModelo, Long>{
    PessoaModelo findByCodigo(Long codigo);
    
    Long countBycodigo(Long codigo);

}
