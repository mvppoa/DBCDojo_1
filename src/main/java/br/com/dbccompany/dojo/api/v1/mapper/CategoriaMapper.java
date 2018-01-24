package br.com.dbccompany.dojo.api.v1.mapper;

import br.com.dbccompany.dojo.api.v1.model.CategoriaDTO;
import br.com.dbccompany.dojo.domain.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author mfachinelli
 */
@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO categoriaParaCategoriaDTO(Categoria categoria);
}
