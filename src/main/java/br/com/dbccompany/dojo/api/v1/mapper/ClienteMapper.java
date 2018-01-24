package br.com.dbccompany.dojo.api.v1.mapper;

import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import br.com.dbccompany.dojo.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author mfachinelli
 */
@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteToClienteDTO(Cliente cliente);

    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
}
