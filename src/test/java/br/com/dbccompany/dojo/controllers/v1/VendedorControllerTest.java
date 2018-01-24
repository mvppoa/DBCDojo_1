package br.com.dbccompany.dojo.controllers.v1;

import br.com.dbccompany.dojo.services.VendedorService;
import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.api.v1.model.VendedorListDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static br.com.dbccompany.dojo.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendedorController.class})
public class VendedorControllerTest {

    @MockBean //provido pelo Contexto Spring
            VendedorService vendedorService;

    @Autowired
    MockMvc mockMvc; //provido pelo Contexto Spring

    VendedorDTO vendedorDTO1;
    VendedorDTO vendedorDTO2;

    @Before
    public void setUp() {
        vendedorDTO1 = new VendedorDTO("Vendedor 1", VendedorController.BASE_URL + "/1");
        vendedorDTO2 = new VendedorDTO("Vendedor 2", VendedorController.BASE_URL + "/2");
    }

    @Test
    public void getVendedorList() throws Exception {
        VendedorListDTO vendedorListDTO = new VendedorListDTO(Arrays.asList(vendedorDTO1, vendedorDTO2));

        given(vendedorService.getAllVendedores()).willReturn(vendedorListDTO);

        mockMvc.perform(get(VendedorController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendedores", hasSize(2)));
    }

    @Test
    public void getVendedorById() throws Exception {

        given(vendedorService.getVendedorById(anyLong())).willReturn(vendedorDTO1);

        mockMvc.perform(get(VendedorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(vendedorDTO1.getNome())));
    }

    @Test
    public void createNewVendedor() throws Exception {

        given(vendedorService.createNewVendedor(vendedorDTO1)).willReturn(vendedorDTO1);

        mockMvc.perform(post(VendedorController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(vendedorDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", equalTo(vendedorDTO1.getNome())));
    }

    @Test
    public void updateVendedor() throws Exception {

        given(vendedorService.saveVendedor(anyLong(), any(VendedorDTO.class))).willReturn(vendedorDTO1);

        mockMvc.perform(put(VendedorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendedorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(vendedorDTO1.getNome())));
    }

    @Test
    public void patchVendedor() throws Exception {
        given(vendedorService.saveVendedor(anyLong(), any(VendedorDTO.class))).willReturn(vendedorDTO1);

        mockMvc.perform(patch(VendedorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendedorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo(vendedorDTO1.getNome())));
    }

    @Test
    public void deleteVendedor() throws Exception {
        mockMvc.perform(delete(VendedorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendedorService).should().deleteVendedorById(anyLong());

    }
}