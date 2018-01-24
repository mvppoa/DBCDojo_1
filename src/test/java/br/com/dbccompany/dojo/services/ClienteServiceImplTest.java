package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.mapper.ClienteMapper;
import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import br.com.dbccompany.dojo.domain.Cliente;
import br.com.dbccompany.dojo.repositories.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @Author mfachinelli
 */
public class ClienteServiceImplTest {

    @Mock
    ClienteRepository clienteRepository;

    ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    ClienteService clienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        clienteService = new ClienteServiceImpl(clienteMapper, clienteRepository);
    }

    @Test
    public void getAllClientes() {
        //dado
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setPrimeiroNome("Michale");
        cliente1.setUltimoNome("Weston");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setPrimeiroNome("Sam");
        cliente2.setUltimoNome("Axe");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        //quando
        List<ClienteDTO> clienteDTOS = clienteService.getAllClientes();

        //então
        assertEquals(2, clienteDTOS.size());

    }

    @Test
    public void getClienteById() {
        //dado
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setPrimeiroNome("Michale");
        cliente1.setUltimoNome("Weston");

        when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(cliente1));

        //quando
        ClienteDTO clienteDTO = clienteService.getClienteById(1L);

        assertEquals("Michale", clienteDTO.getPrimeiroNome());
    }

    @Test
    public void createNewCliente() {

        //dado
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setPrimeiroNome("Jim");

        Cliente savedCliente = new Cliente();
        savedCliente.setPrimeiroNome(clienteDTO.getPrimeiroNome());
        savedCliente.setUltimoNome(clienteDTO.getUltimoNome());
        savedCliente.setId(1L);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);

        //quando
        ClienteDTO savedDto = clienteService.createNewCliente(clienteDTO);

        //então
        assertEquals(clienteDTO.getPrimeiroNome(), savedDto.getPrimeiroNome());
        assertEquals("/api/v1/clientes/1", savedDto.getClienteUrl());
    }

    @Test
    public void saveClienteByDTO() {

        //dado
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setPrimeiroNome("Jim");

        Cliente savedCliente = new Cliente();
        savedCliente.setPrimeiroNome(clienteDTO.getPrimeiroNome());
        savedCliente.setUltimoNome(clienteDTO.getUltimoNome());
        savedCliente.setId(1L);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);

        //quando
        ClienteDTO savedDto = clienteService.saveClienteByDTO(1L, clienteDTO);

        //então
        assertEquals(clienteDTO.getPrimeiroNome(), savedDto.getPrimeiroNome());
        assertEquals("/api/v1/clientes/1", savedDto.getClienteUrl());
    }

    @Test
    public void deleteClienteById() {

        Long id = 1L;

        clienteRepository.deleteById(id);

        verify(clienteRepository, times(1)).deleteById(anyLong());
    }

}