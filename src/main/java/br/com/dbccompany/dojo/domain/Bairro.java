package br.com.dbccompany.dojo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Municipio municipio;


}
