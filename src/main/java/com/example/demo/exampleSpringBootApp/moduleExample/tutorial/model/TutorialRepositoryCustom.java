package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model;

import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface TutorialRepositoryCustom {

    Page<Tutorial> findAllTutorialDTOList(Specification<Tutorial> spec, Pageable page);

}
