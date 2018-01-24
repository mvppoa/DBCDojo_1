package br.com.dbccompany.dojo.repositories;

import br.com.dbccompany.dojo.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author mfachinelli
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String name);

}
