package br.com.dbccompany.dojo.controllers.v1;

import br.com.dbccompany.dojo.controllers.RestResponseEntityExceptionHandler;
import br.com.dbccompany.dojo.services.ClienteService;
import br.com.dbccompany.dojo.services.ResourceNotFoundException;
import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author mfachinelli
 */
public class ClienteControllerTest extends AbstractRestControllerTest {

    @Mock
    ClienteService clienteService;

    @InjectMocks
    ClienteController clienteController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListClientes() throws Exception {

        //dado
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setPrimeiroNome("Michale");
        cliente1.setUltimoNome("Weston");
        cliente1.setClienteUrl(ClienteController.BASE_URL + "/1");

        ClienteDTO cliente2 = new ClienteDTO();
        cliente2.setPrimeiroNome("Sam");
        cliente2.setUltimoNome("Axe");
        cliente2.setClienteUrl(ClienteController.BASE_URL + "/2");

        when(clienteService.getAllClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        mockMvc.perform(get(ClienteController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientes", hasSize(2)));
    }

    @Test
    public void testGetClienteById() throws Exception {

        //dado
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setPrimeiroNome("Michale");
        cliente1.setUltimoNome("Weston");
        cliente1.setClienteUrl(ClienteController.BASE_URL + "/1");

        when(clienteService.getClienteById(anyLong())).thenReturn(cliente1);

        //quando
        mockMvc.perform(get(ClienteController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primeiro_nome", equalTo("Michale")));
    }

    @Test
    public void createNewCliente() throws Exception {
        //dado
        ClienteDTO cliente = new ClienteDTO();
        cliente.setPrimeiroNome("Fred");
        cliente.setUltimoNome("Flintstone");

        ClienteDTO returnDTO = new ClienteDTO();
        returnDTO.setPrimeiroNome(cliente.getPrimeiroNome());
        returnDTO.setUltimoNome(cliente.getUltimoNome());
        returnDTO.setClienteUrl(ClienteController.BASE_URL + "/1");

        when(clienteService.createNewCliente(cliente)).thenReturn(returnDTO);

        //quando/then
        mockMvc.perform(post(ClienteController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.primeiro_nome", equalTo("Fred")))
                .andExpect(jsonPath("$.cliente_url", equalTo(ClienteController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        //dado
        ClienteDTO cliente = new ClienteDTO();
        cliente.setPrimeiroNome("Fred");
        cliente.setUltimoNome("Flintstone");

        ClienteDTO returnDTO = new ClienteDTO();
        returnDTO.setPrimeiroNome(cliente.getPrimeiroNome());
        returnDTO.setUltimoNome(cliente.getUltimoNome());
        returnDTO.setClienteUrl(ClienteController.BASE_URL + "/1");

        when(clienteService.saveClienteByDTO(anyLong(), any(ClienteDTO.class))).thenReturn(returnDTO);

        //quando/then
        mockMvc.perform(put(ClienteController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primeiro_nome", equalTo("Fred")))
                .andExpect(jsonPath("$.ultimo_nome", equalTo("Flintstone")))
                .andExpect(jsonPath("$.cliente_url", equalTo(ClienteController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchCliente() throws Exception {

        //dado
        ClienteDTO cliente = new ClienteDTO();
        cliente.setPrimeiroNome("Fred");

        ClienteDTO returnDTO = new ClienteDTO();
        returnDTO.setPrimeiroNome(cliente.getPrimeiroNome());
        returnDTO.setUltimoNome("Flintstone");
        returnDTO.setClienteUrl(ClienteController.BASE_URL + "/1");

        when(clienteService.patchCliente(anyLong(), any(ClienteDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(ClienteController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primeiro_nome", equalTo("Fred")))
                .andExpect(jsonPath("$.ultimo_nome", equalTo("Flintstone")))
                .andExpect(jsonPath("$.cliente_url", equalTo(ClienteController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteCliente() throws Exception {

        mockMvc.perform(delete(ClienteController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clienteService).deleteClienteById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(clienteService.getClienteById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(ClienteController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}