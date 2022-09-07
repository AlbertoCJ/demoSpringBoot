package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.filters;

import com.example.demo.exampleSpringBootApp.commons.filter.SpecificationsBuilder;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.data.jpa.domain.Specification;

public class TutorialFilter {

    public static Specification<Tutorial> getTutorialSpecification(
            String title,
            String desc,
            Boolean published,
            String autorName
    ) {

        SpecificationsBuilder<Tutorial> spec = new SpecificationsBuilder<>();

        if (title != null) {
            spec.with(TutorialSpecification.TITLE, title, false);
        }

        if (desc != null) {
            spec.with(TutorialSpecification.DESCRIPTION, desc, false);
        }

        if (published != null) {
            spec.with(TutorialSpecification.PUBLISHED, published, false);
        }

        if (autorName != null) {
            spec.with(TutorialSpecification.AUTOR_NAME, autorName, false);
        }

        return Specification.where(spec.build(TutorialSpecification::new));
    }

}
