package com.example.demo.exampleSpringBootApp.domain.mappers;

import com.example.demo.exampleSpringBootApp.domain.mappers.references.ReferenceMapper;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.dto.AutorDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface AutorMapper {

    /* Con estos dos map y el uses = { ReferenceMapper.class },
    * el mapper es capaz de obtener una lista de Autor desde una lista de ids
    * */
    Set<Autor> map(Set<Long> value);
    Autor map(Long value);


    AutorDTO toAutorDTO(Autor autor);

    Autor ToAutor(AutorDTO autorDTO);

    @Mapping(target="id", ignore=true)
    @Mapping(target="deleted", ignore=true)
    Autor createAutor(AutorDTO autorDTO);

    @Mapping(target="id", ignore=true)
    @Mapping(target="deleted", ignore=true)
    void updateAutor(AutorDTO autorDTO,  @MappingTarget Autor autor);

}
