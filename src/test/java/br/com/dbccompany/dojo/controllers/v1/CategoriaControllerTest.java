package br.com.dbccompany.dojo.controllers.v1;

import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;
import br.com.dbccompany.dojo.controllers.RestResponseEntityExceptionHandler;
import br.com.dbccompany.dojo.services.CategoriaService;
import br.com.dbccompany.dojo.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author mfachinelli
 */
public class CategoriaControllerTest {

    public static final String NAME = "Jim";

    @Mock
    CategoriaService categoriaService;

    @InjectMocks
    CategoriaController categoriaController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

    }

    @Test
    public void testListCategories() throws Exception {
        CategoriaDTO categoria1 = new CategoriaDTO();
        categoria1.setId(1L);
        categoria1.setNome(NAME);

        CategoriaDTO categoria2 = new CategoriaDTO();
        categoria2.setId(2L);
        categoria2.setNome("Bob");

        List<CategoriaDTO> categories = Arrays.asList(categoria1, categoria2);

        when(categoriaService.getAllCategorias()).thenReturn(categories);

        mockMvc.perform(get(CategoriaController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categorias", hasSize(2)));
    }

    @Test
    public void testGetByNameCategories() throws Exception {
        CategoriaDTO categoria1 = new CategoriaDTO();
        categoria1.setId(1L);
        categoria1.setNome(NAME);

        when(categoriaService.getCategoriasByNome(anyString())).thenReturn(categoria1);

        mockMvc.perform(get(CategoriaController.BASE_URL + "/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(NAME)));
    }

    @Test
    public void testGetByNameNotFound() throws Exception {

        when(categoriaService.getCategoriasByNome(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CategoriaController.BASE_URL + "/Foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}