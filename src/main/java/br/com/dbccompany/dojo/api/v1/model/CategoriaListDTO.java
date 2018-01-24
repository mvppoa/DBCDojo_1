package br.com.dbccompany.dojo.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author mfachinelli
 */
@Data
@AllArgsConstructor
public class CategoriaListDTO {

    List<CategoriaDTO> categorias;

}
