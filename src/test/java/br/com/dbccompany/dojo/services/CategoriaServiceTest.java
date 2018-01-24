package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.mapper.CategoriaMapper;
import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;
import br.com.dbccompany.dojo.domain.Categoria;
import br.com.dbccompany.dojo.repositories.CategoriaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Author mfachinelli
 */
public class CategoriaServiceTest {

    public static final Long ID = 2L;
    public static final String NOME = "Jimmy";
    CategoriaService categoriaService;

    @Mock
    CategoriaRepository categoriaRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoriaService = new CategoriaServiceImpl(CategoriaMapper.INSTANCE, categoriaRepository);
    }

    @Test
    public void getAllCategories() {

        //dado
        List<Categoria> categories = Arrays.asList(new Categoria(), new Categoria(), new Categoria());

        when(categoriaRepository.findAll()).thenReturn(categories);

        //quando
        List<CategoriaDTO> categoriaDTOS = categoriaService.getAllCategorias();

        //então
        assertEquals(3, categoriaDTOS.size());

    }

    @Test
    public void getCategoriaByName() {

        //dado
        Categoria categoria = new Categoria();
        categoria.setId(ID);
        categoria.setNome(NOME);

        when(categoriaRepository.findByNome(anyString())).thenReturn(categoria);

        //quando
        CategoriaDTO categoriaDTO = categoriaService.getCategoriasByNome(NOME);

        //então
        assertEquals(ID, categoriaDTO.getId());
        assertEquals(NOME, categoriaDTO.getNome());

    }

}