package br.com.dbccompany.dojo.api.v1.mapper;

import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import br.com.dbccompany.dojo.domain.Cliente;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author mfachinelli
 */
public class ClienteMapperTest {

    public static final String FIRSTNAME = "João";
    public static final String LASTNAME = "Ninguém";
    ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @Test
    public void clienteToClienteDTO() {
        //dado
        Cliente cliente = new Cliente();
        cliente.setPrimeiroNome(FIRSTNAME);
        cliente.setUltimoNome(LASTNAME);

        //quando
        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        //então
        assertEquals(FIRSTNAME, clienteDTO.getPrimeiroNome());
        assertEquals(LASTNAME, clienteDTO.getUltimoNome());

    }

}