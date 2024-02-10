package br.com.backproduto.backproduto.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.backproduto.backproduto.modelo.PessoaModelo;
import br.com.backproduto.backproduto.repositorio.PessoaRepository;
import br.com.backproduto.backproduto.util.Mensagem;

@Service
public class PessoaServico {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private Mensagem mensagem;
    
    public ResponseEntity<?> cadastrarPessoa(PessoaModelo pessoaModelo){
        //TODO: fazer validação 
        return new ResponseEntity<PessoaModelo>(pessoaRepository.save(pessoaModelo), HttpStatus.CREATED);
    }
    //Metodo para buscar todos as pessoas
    public Iterable<PessoaModelo> listarTodasPessoas(){
        return pessoaRepository.findAll();
    }
    //Metodo para buscar uma pessoa por codigo
    public Optional<PessoaModelo> buscarPessoaPorId(Long codigo){
        return pessoaRepository.findById(codigo);
    }
    //Metodo para editar pessoa
    public ResponseEntity<?> editarPessoa(PessoaModelo pessoaModelo){
        //TODO: criar validação
        return new ResponseEntity<PessoaModelo>(pessoaRepository.save(pessoaModelo), HttpStatus.OK);
    }
    //Metodo para remover uma pessoa
    public ResponseEntity<?> removerPessoa(Long codigo){
        if (pessoaRepository.countBycodigo(codigo) == 0) {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
            
        } else {
            PessoaModelo pessoaModelo = pessoaRepository.findByCodigo(codigo);
            pessoaRepository.delete(pessoaModelo);
            
            mensagem.setMensagem("Produto removido com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
            
        }
    }
}
