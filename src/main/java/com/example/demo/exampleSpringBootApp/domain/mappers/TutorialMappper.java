package com.example.demo.exampleSpringBootApp.domain.mappers;

import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto.TutorialListDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto.TutorialSaveDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses= { AutorMapper.class})
public interface TutorialMappper {

    TutorialListDTO toTutorialListDTO(Tutorial tutorial);

    @Mapping(target="description", source = "desc")
    Tutorial ToTutorial(TutorialSaveDTO tutorialSaveDTO);

    @Mapping(target="id", ignore=true)
    @Mapping(target="description", source = "desc")
    @Mapping(target = "published", constant = "false")
    Tutorial createTutorial(TutorialSaveDTO tutorialSaveDTO);

    @Mapping(target="id", ignore=true)
    @Mapping(target="description", source = "desc")
    void updateTutorial(TutorialSaveDTO dto,  @MappingTarget Tutorial tutorial);

}
