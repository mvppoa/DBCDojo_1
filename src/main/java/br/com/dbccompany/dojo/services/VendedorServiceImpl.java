package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.mapper.VendedorMapper;
import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.api.v1.model.VendedorListDTO;
import br.com.dbccompany.dojo.controllers.v1.VendedorController;
import br.com.dbccompany.dojo.domain.Vendedor;
import br.com.dbccompany.dojo.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author mfachinelli
 */
@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorMapper vendedorMapper;
    private final VendedorRepository vendedorRepository;

    public VendedorServiceImpl(VendedorMapper vendedorMapper, VendedorRepository vendedorRepository) {
        this.vendedorMapper = vendedorMapper;
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public VendedorDTO getVendedorById(Long id) {
        return vendedorRepository.findById(id)
                .map(vendedorMapper::vendedorParaVendedorDTO)
                .map(vendedorDTO -> {
                    vendedorDTO.setVendedorUrl(getVendedorUrl(id));
                    return vendedorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendedorListDTO getAllVendedores() {
        List<VendedorDTO> vendedorDTOS = vendedorRepository
                .findAll()
                .stream()
                .map(vendedor -> {
                    VendedorDTO vendedorDTO = vendedorMapper.vendedorParaVendedorDTO(vendedor);
                    vendedorDTO.setVendedorUrl(getVendedorUrl(vendedor.getId()));
                    return vendedorDTO;
                })
                .collect(Collectors.toList());

        return new VendedorListDTO(vendedorDTOS);
    }

    @Override
    public VendedorDTO createNewVendedor(VendedorDTO vendedorDTO) {
        return saveAndReturnDTO(vendedorMapper.vendedorDTOParaVendedor(vendedorDTO));
    }

    @Override
    public VendedorDTO saveVendedor(Long id, VendedorDTO vendedorDTO) {

        Vendedor vendedorToSave = vendedorMapper.vendedorDTOParaVendedor(vendedorDTO);
        vendedorToSave.setId(id);

        return saveAndReturnDTO(vendedorToSave);
    }

    @Override
    public VendedorDTO patchVendedor(Long id, VendedorDTO vendedorDTO) {
        return vendedorRepository.findById(id)
                .map(vendedor -> {
                    //todo if more properties, add more if statements

                    if(vendedorDTO.getNome() != null){
                        vendedor.setNome(vendedorDTO.getNome());
                    }

                    return saveAndReturnDTO(vendedor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendedorById(Long id) {
        vendedorRepository.deleteById(id);
    }

    private String getVendedorUrl(Long id) {
        return VendedorController.BASE_URL + "/" + id;
    }

    private VendedorDTO saveAndReturnDTO(Vendedor vendedor) {
        Vendedor savedVendedor = vendedorRepository.save(vendedor);

        VendedorDTO returnDto = vendedorMapper.vendedorParaVendedorDTO(savedVendedor);

        returnDto.setVendedorUrl(getVendedorUrl(savedVendedor.getId()));

        return returnDto;
    }
}
