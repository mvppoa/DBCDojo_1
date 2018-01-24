package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;

import java.util.List;

/**
 * @Author mfachinelli
 */
public interface ClienteService {

    List<ClienteDTO> getAllClientes();

    ClienteDTO getClienteById(Long id);

    ClienteDTO createNewCliente(ClienteDTO clienteDTO);

    ClienteDTO saveClienteByDTO(Long id, ClienteDTO clienteDTO);

    ClienteDTO patchCliente(Long id, ClienteDTO clienteDTO);

    void deleteClienteById(Long id);
}
