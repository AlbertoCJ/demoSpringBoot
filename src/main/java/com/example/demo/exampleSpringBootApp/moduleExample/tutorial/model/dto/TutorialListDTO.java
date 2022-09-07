package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto;

import com.example.demo.exampleSpringBootApp.domain.enums.TutorialType;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.dto.AutorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TutorialListDTO extends TutorialDTO {

    private Set<AutorDTO> autors;

    public TutorialListDTO() {
        super();
        this.autors = new HashSet<>();
    }

    /* Se usa para generar el construct al hacer criteriaquery (TutorialRepositoryCustomImp) */
    public TutorialListDTO(Long id, String title, String desc, boolean published, TutorialType tutorialType, Set<AutorDTO> autors) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.published = published;
        this.tutorialType = tutorialType.name();
        this.autors = autors;
    }

}
