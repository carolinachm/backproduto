package br.com.backproduto.backproduto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "tb_endereco")
public class EnderecoModelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String endereco;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
    
}
