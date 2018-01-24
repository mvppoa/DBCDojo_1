package br.com.dbccompany.dojo.api.v1.mapper;

import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;
import br.com.dbccompany.dojo.domain.Categoria;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author mfachinelli
 */
public class CategoriaMapperTest {

    public static final String JOANA = "Joana";
    public static final long ID = 1L;

    CategoriaMapper categoriaMapper = CategoriaMapper.INSTANCE;

    @Test
    public void categoriaToCategoriaDTO() {

        //dado
        Categoria categoria = new Categoria();
        categoria.setNome(JOANA);
        categoria.setId(ID);

        //quando
        CategoriaDTO categoriaDTO = categoriaMapper.categoriaParaCategoriaDTO(categoria);

        //então
        assertEquals(Long.valueOf(ID), categoriaDTO.getId());
        assertEquals(JOANA, categoriaDTO.getNome());
    }

}