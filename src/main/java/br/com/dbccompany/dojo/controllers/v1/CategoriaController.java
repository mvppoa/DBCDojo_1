package br.com.dbccompany.dojo.controllers.v1;

import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;
import br.com.dbccompany.dojo.api.v1.model.CategoriaListDTO;
import br.com.dbccompany.dojo.services.CategoriaService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author mfachinelli
 */
@Api("Controlador de categorias")
@RestController
@RequestMapping(CategoriaController.BASE_URL)
public class CategoriaController {

    public static final String BASE_URL = "/api/v1/categorias";

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoriaListDTO getallCatetorias(){
        return new CategoriaListDTO(categoriaService.getAllCategorias());
    }

    @GetMapping("{nome}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO getCategoriaByName(@PathVariable String nome){
        return categoriaService.getCategoriasByNome(nome);
    }
}
