package br.com.dbccompany.dojo.controllers.v1;

import br.com.dbccompany.dojo.api.v1.model.ClienteDTO;
import br.com.dbccompany.dojo.api.v1.model.ClienteListDTO;
import br.com.dbccompany.dojo.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author mfachinelli
 */
@Api("Controlador de cliente")
@RestController
@RequestMapping(ClienteController.BASE_URL)
public class ClienteController {

    public static final String BASE_URL = "/api/v1/clientes";

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ApiOperation(value= "Esta é uma lista de clientes", notes="Retorna todo o objeto de clientes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClienteListDTO getListOfClientes(){
        return new ClienteListDTO(clienteService.getAllClientes());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getClienteById(@PathVariable Long id){
        return clienteService.getClienteById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO createNewCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.createNewCliente(clienteDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.saveClienteByDTO(id, clienteDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO patchCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.patchCliente(id, clienteDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCliente(@PathVariable Long id){
        clienteService.deleteClienteById(id);
    }
}
