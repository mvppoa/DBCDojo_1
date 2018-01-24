package br.com.dbccompany.dojo.domain;

import javax.persistence.*;

@Entity
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private UniaoFederativa uniaoFederativa;

}
