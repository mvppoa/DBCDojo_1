package br.com.dbccompany.dojo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;

    @ManyToMany(mappedBy = "enderecos")
    private Set<Cliente> clientes;

    @ManyToOne
    private Bairro bairro;



}
