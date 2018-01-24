package br.com.dbccompany.dojo.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author mfachinelli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorListDTO {

    List<VendedorDTO> vendedores;
}
