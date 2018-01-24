package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.api.v1.model.VendedorListDTO;


/**
 * @Author mfachinelli
 */
public interface VendedorService {

    VendedorDTO getVendedorById(Long id);

    VendedorListDTO getAllVendedores();

    VendedorDTO createNewVendedor(VendedorDTO vendedorDTO);

    VendedorDTO saveVendedor(Long id, VendedorDTO vendedorDTO);

    VendedorDTO patchVendedor(Long id, VendedorDTO vendedorDTO);

    void deleteVendedorById(Long id);
}
