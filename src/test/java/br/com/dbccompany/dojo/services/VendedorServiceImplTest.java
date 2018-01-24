package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.mapper.VendedorMapper;
import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.api.v1.model.VendedorListDTO;
import br.com.dbccompany.dojo.domain.Vendedor;
import br.com.dbccompany.dojo.repositories.VendedorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * @Author mfachinelli
 */
public class VendedorServiceImplTest {

    public static final String NOME = "My Vendedor";
    public static final long ID_1 = 1L;

    public static final String NAME_2 = "My Vendedor";
    public static final long ID_2 = 1L;

    @Mock
    VendedorRepository vendedorRepository;

    VendedorService vendedorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendedorService = new VendedorServiceImpl(VendedorMapper.INSTANCE, vendedorRepository);
    }

    @Test
    public void getVendedorById() {
        //Para
        Vendedor vendedor = getVendedor1();

        given(vendedorRepository.findById(anyLong())).willReturn(Optional.of(vendedor));

        //quando
        VendedorDTO vendedorDTO = vendedorService.getVendedorById(1L);

        //então
        then(vendedorRepository).should(times(1)).findById(anyLong());

        //JUnit assertThat
        assertThat(vendedorDTO.getNome(), is(equalTo(NOME)));
    }



    @Test(expected = ResourceNotFoundException.class)
    public void getVendedorByIdNotFound() {
        //dado
        given(vendedorRepository.findById(anyLong())).willReturn(Optional.empty());

        //quando
        VendedorDTO vendedorDTO = vendedorService.getVendedorById(1L);

        //então
        then(vendedorRepository).should(times(1)).findById(anyLong());

    }

    @Test
    public void getAllVendedors() {
        //dado
        List<Vendedor> vendedors = Arrays.asList(getVendedor1(), getVendedor2());
        given(vendedorRepository.findAll()).willReturn(vendedors);

        //quando
        VendedorListDTO vendedorListDTO = vendedorService.getAllVendedores();

        //então
        then(vendedorRepository).should(times(1)).findAll();
        assertThat(vendedorListDTO.getVendedores().size(), is(equalTo(2)));
    }

    @Test
    public void createNewVendedor() {
        //dado
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(NOME);

        Vendedor vendedor = getVendedor1();

        given(vendedorRepository.save(any(Vendedor.class))).willReturn(vendedor);

        //quando
        VendedorDTO savedVendedorDTO = vendedorService.createNewVendedor(vendedorDTO);

        //então
        then(vendedorRepository).should().save(any(Vendedor.class));
        assertThat(savedVendedorDTO.getVendedorUrl(), containsString("1"));

    }

    @Test
    public void saveVendedorByDTO() {

        //dado
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(NOME);

        Vendedor vendedor = getVendedor1();

        given(vendedorRepository.save(any(Vendedor.class))).willReturn(vendedor);

        //quando
        VendedorDTO savedVendedorDTO = vendedorService.saveVendedor(ID_1, vendedorDTO);

        //então
        then(vendedorRepository).should().save(any(Vendedor.class));
        assertThat(savedVendedorDTO.getVendedorUrl(), containsString("1"));
    }

    @Test
    public void patchVendedor() {
        //dado
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(NOME);

        Vendedor vendedor = getVendedor1();

        given(vendedorRepository.findById(anyLong())).willReturn(Optional.of(vendedor));
        given(vendedorRepository.save(any(Vendedor.class))).willReturn(vendedor);

        //quando

        VendedorDTO savedVendedorDTO = vendedorService.patchVendedor(ID_1, vendedorDTO);

        //então
        then(vendedorRepository).should().save(any(Vendedor.class));
        then(vendedorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendedorDTO.getVendedorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendedorById() {

        //quando
        vendedorService.deleteVendedorById(1L);

        //então
        then(vendedorRepository).should().deleteById(anyLong());
    }

    private Vendedor getVendedor1() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(NOME);
        vendedor.setId(ID_1);
        return vendedor;
    }

    private Vendedor getVendedor2() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(NAME_2);
        vendedor.setId(ID_2);
        return vendedor;
    }
}