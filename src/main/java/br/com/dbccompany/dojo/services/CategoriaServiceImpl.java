package br.com.dbccompany.dojo.services;

import br.com.dbccompany.dojo.api.v1.mapper.CategoriaMapper;
import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;
import br.com.dbccompany.dojo.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author mfachinelli
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaMapper categoriaMapper, CategoriaRepository categoriaRepository) {
        this.categoriaMapper = categoriaMapper;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> getAllCategorias() {

        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::categoriaParaCategoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO getCategoriasByNome(String name) {
        return categoriaMapper.categoriaParaCategoriaDTO(categoriaRepository.findByNome(name));
    }
}
