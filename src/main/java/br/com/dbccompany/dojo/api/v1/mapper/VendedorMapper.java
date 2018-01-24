package br.com.dbccompany.dojo.api.v1.mapper;

import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.domain.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author mfachinelli
 */
@Mapper
public interface VendedorMapper {

    VendedorMapper INSTANCE = Mappers.getMapper(VendedorMapper.class);

    VendedorDTO vendedorParaVendedorDTO(Vendedor vendedor);

    Vendedor vendedorDTOParaVendedor(VendedorDTO vendedorDTO);
}
