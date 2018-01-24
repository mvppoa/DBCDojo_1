package br.com.dbccompany.dojo.domain;

import javax.persistence.*;

@Entity
public class UniaoFederativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "sigla", length = 2)
    private String sigla;

    @ManyToOne
    private Pais pais;

}
