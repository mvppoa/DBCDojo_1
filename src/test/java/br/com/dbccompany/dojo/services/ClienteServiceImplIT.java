package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.mapper.ClienteMapper;
import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import br.com.dbccompany.dojo.bootstrap.Bootstrap;
import br.com.dbccompany.dojo.domain.Cliente;
import br.com.dbccompany.dojo.repositories.CategoriaRepository;
import br.com.dbccompany.dojo.repositories.ClienteRepository;
import br.com.dbccompany.dojo.repositories.VendedorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * @Author mfachinelli
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteServiceImplIT {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    VendedorRepository vendedorRepository;

    ClienteService clienteService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Cliente Data");
        System.out.println(clienteRepository.findAll().size());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoriaRepository, clienteRepository, vendedorRepository);
        bootstrap.run(); //load data

        clienteService = new ClienteServiceImpl(ClienteMapper.INSTANCE, clienteRepository);
    }

    @Test
    public void patchClienteUpdatePrimeiroNome() {
        String updatedNome = "UpdatedNome";
        long id = getClienteIdValue();

        Cliente originalCliente = clienteRepository.getOne(id);
        assertNotNull(originalCliente);
        //save original first nome
        String originalPrimeiroNome = originalCliente.getPrimeiroNome();
        String originalUltimoNome = originalCliente.getUltimoNome();

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setPrimeiroNome(updatedNome);

        clienteService.patchCliente(id, clienteDTO);

        Cliente updatedCliente = clienteRepository.findById(id).get();

        assertNotNull(updatedCliente);
        assertEquals(updatedNome, updatedCliente.getPrimeiroNome());
        assertThat(originalPrimeiroNome, not(equalTo(updatedCliente.getPrimeiroNome())));
        assertThat(originalUltimoNome, equalTo(updatedCliente.getUltimoNome()));
    }

    @Test
    public void patchClienteUpdateUltimoNome() {
        String updatedNome = "UpdatedNome";
        long id = getClienteIdValue();

        Cliente originalCliente = clienteRepository.getOne(id);
        assertNotNull(originalCliente);

        //save original first/last nome
        String originalPrimeiroNome = originalCliente.getPrimeiroNome();
        String originalUltimoNome = originalCliente.getUltimoNome();

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setUltimoNome(updatedNome);

        clienteService.patchCliente(id, clienteDTO);

        Cliente updatedCliente = clienteRepository.findById(id).get();

        assertNotNull(updatedCliente);
        assertEquals(updatedNome, updatedCliente.getUltimoNome());
        assertThat(originalPrimeiroNome, equalTo(updatedCliente.getPrimeiroNome()));
        assertThat(originalUltimoNome, not(equalTo(updatedCliente.getUltimoNome())));
    }

    private Long getClienteIdValue(){
        List<Cliente> clientes = clienteRepository.findAll();

        System.out.println("Clientes Found: " + clientes.size());

        //return first id
        return clientes.get(0).getId();
    }
}
