package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;

import java.util.List;

/**
 * @Author mfachinelli
 */
public interface CategoriaService {

    List<CategoriaDTO> getAllCategorias();

    CategoriaDTO getCategoriasByNome(String nome);
}
