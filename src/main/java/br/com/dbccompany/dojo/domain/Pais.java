package br.com.dbccompany.dojo.domain;

import javax.persistence.*;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
