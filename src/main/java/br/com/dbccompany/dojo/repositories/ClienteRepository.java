package br.com.dbccompany.dojo.repositories;

import br.com.dbccompany.dojo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author mfachinelli
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
