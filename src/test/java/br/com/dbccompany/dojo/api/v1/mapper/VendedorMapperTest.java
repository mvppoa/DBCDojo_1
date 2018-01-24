package br.com.dbccompany.dojo.api.v1.mapper;

import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.domain.Vendedor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author mfachinelli
 */
public class VendedorMapperTest {

    public static final String NAME = "someName";

    VendedorMapper vendedorMapper = VendedorMapper.INSTANCE;

    @Test
    public void vendedorToVendedorDTO() {
        //dado
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(NAME);

        //quando
        VendedorDTO vendedorDTO = vendedorMapper.vendedorParaVendedorDTO(vendedor);

        //então
        assertEquals(vendedor.getNome(), vendedorDTO.getNome());
    }

    @Test
    public void vendedorDTOtoVendedor() {
        //dado
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(NAME);

        //quando
        Vendedor vendedor = vendedorMapper.vendedorDTOParaVendedor(vendedorDTO);

        //então
        assertEquals(vendedorDTO.getNome(), vendedor.getNome());
    }

}