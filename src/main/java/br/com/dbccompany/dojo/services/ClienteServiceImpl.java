package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.controllers.v1.ClienteController;
import br.com.dbccompany.dojo.api.v1.mapper.ClienteMapper;
import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import br.com.dbccompany.dojo.domain.Cliente;
import br.com.dbccompany.dojo.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author mfachinelli
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository
                .findAll()
                .stream()
                .map(cliente -> {
                   ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);
                   clienteDTO.setClienteUrl(getClienteUrl(cliente.getId()));
                   return clienteDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getClienteById(Long id) {

        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToClienteDTO)
                .map(clienteDTO -> {
                    //set API URL
                    clienteDTO.setClienteUrl(getClienteUrl(id));
                    return clienteDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClienteDTO createNewCliente(ClienteDTO clienteDTO) {

        return saveAndReturnDTO(clienteMapper.clienteDTOToCliente(clienteDTO));
    }

    private ClienteDTO saveAndReturnDTO(Cliente cliente) {
        Cliente savedCliente = clienteRepository.save(cliente);

        ClienteDTO returnDto = clienteMapper.clienteToClienteDTO(savedCliente);

        returnDto.setClienteUrl(getClienteUrl(savedCliente.getId()));

        return returnDto;
    }

    @Override
    public ClienteDTO saveClienteByDTO(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
        cliente.setId(id);

        return saveAndReturnDTO(cliente);
    }

    @Override
    public ClienteDTO patchCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id).map(cliente -> {

            if(clienteDTO.getPrimeiroNome() != null){
                cliente.setPrimeiroNome(clienteDTO.getPrimeiroNome());
            }

            if(clienteDTO.getUltimoNome() != null){
                cliente.setUltimoNome(clienteDTO.getUltimoNome());
            }

            ClienteDTO returnDto = clienteMapper.clienteToClienteDTO(clienteRepository.save(cliente));

            returnDto.setClienteUrl(getClienteUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    private String getClienteUrl(Long id) {
        return ClienteController.BASE_URL + "/" + id;
    }

    @Override
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
