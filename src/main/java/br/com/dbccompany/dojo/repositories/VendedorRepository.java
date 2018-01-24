package br.com.dbccompany.dojo.repositories;

import br.com.dbccompany.dojo.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author mfachinelli
 */
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
