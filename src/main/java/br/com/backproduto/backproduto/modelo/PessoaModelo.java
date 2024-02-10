package br.com.backproduto.backproduto.modelo;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_pressoa")
public class PessoaModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pessoa;
    private String nome;
    private String cpf;
    private String celular;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Local dataNascimento;
    private String sexo;

    
}
