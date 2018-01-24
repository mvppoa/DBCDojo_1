package br.com.dbccompany.dojo.controllers.v1;

import br.com.dbccompany.dojo.api.v1.model.VendedorDTO;
import br.com.dbccompany.dojo.api.v1.model.VendedorListDTO;
import br.com.dbccompany.dojo.services.VendedorService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author mfachinelli
 */
@Api("Controlador de vendedor")
@RestController
@RequestMapping(VendedorController.BASE_URL)
public class VendedorController {

    public static final String BASE_URL = "/api/v1/vendedores";

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendedorListDTO getVendedorList(){
        return vendedorService.getAllVendedores();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendedorDTO getVendedorById(@PathVariable Long id){
        return vendedorService.getVendedorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendedorDTO createNewVendedor(@RequestBody VendedorDTO vendedorDTO){
        return vendedorService.createNewVendedor(vendedorDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendedorDTO updateVendedor(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO){
        return vendedorService.saveVendedor(id, vendedorDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendedorDTO patchVendedor(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO){
        return vendedorService.saveVendedor(id, vendedorDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendedor(@PathVariable Long id){
        vendedorService.deleteVendedorById(id);
    }
}
