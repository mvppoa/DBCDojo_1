package br.com.dbccompany.dojo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jt on 9/27/17.
 */
@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String primeiroNome;
    private String ultimoNome;

    @ManyToMany
//    @JoinTable(name="clientes_enderecos",joinColumns = @JoinColumn(name="cliente_id"),
//            inverseJoinColumns = @JoinColumn(name="endereco_id"))
    private Set<Endereco> enderecos;
}
