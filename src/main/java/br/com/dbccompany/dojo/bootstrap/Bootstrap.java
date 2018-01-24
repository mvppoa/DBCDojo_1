package br.com.dbccompany.dojo.bootstrap;

import br.com.dbccompany.dojo.domain.Categoria;
import br.com.dbccompany.dojo.domain.Cliente;
import br.com.dbccompany.dojo.domain.Vendedor;
import br.com.dbccompany.dojo.repositories.CategoriaRepository;
import br.com.dbccompany.dojo.repositories.ClienteRepository;
import br.com.dbccompany.dojo.repositories.VendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author mfachinelli
 */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoriaRepository categoriaRespository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;

    public Bootstrap(CategoriaRepository categoriaRespository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository) {
        this.categoriaRespository = categoriaRespository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public void run(String... args) {
        loadCategorias();
        loadClientes();
        loadVendedores();
    }

    private void loadVendedores() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.setNome("Vendedor 1");
        vendedorRepository.save(vendedor1);

        Vendedor vendedor2 = new Vendedor();
        vendedor2.setNome("Vendedor 2");
        vendedorRepository.save(vendedor2);

    }

    private void loadCategorias() {
        Categoria frutas = new Categoria();
        frutas.setNome("Frutas");

        Categoria seca = new Categoria();
        seca.setNome("Seca");

        Categoria fesca = new Categoria();
        fesca.setNome("Fresca");

        Categoria exotica = new Categoria();
        exotica.setNome("Exótica");

        Categoria sementes = new Categoria();
        sementes.setNome("Sementes");

        categoriaRespository.save(frutas);
        categoriaRespository.save(seca);
        categoriaRespository.save(fesca);
        categoriaRespository.save(exotica);
        categoriaRespository.save(sementes);

        log.info("Categorias carregadas: " + categoriaRespository.count());
    }

    private void loadClientes() {
        //dado
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setPrimeiroNome("Dilma");
        cliente1.setUltimoNome("Estocavento");
        clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setPrimeiroNome("Temer");
        cliente2.setUltimoNome("Recatadodolar");

        clienteRepository.save(cliente2);

        log.info("Clientes carregados: " + clienteRepository.count());
    }
}
