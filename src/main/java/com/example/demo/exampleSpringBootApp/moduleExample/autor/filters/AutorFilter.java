package com.example.demo.exampleSpringBootApp.moduleExample.autor.filters;

import com.example.demo.exampleSpringBootApp.commons.filter.SpecificationsBuilder;
import com.example.demo.exampleSpringBootApp.domain.enums.AutorType;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import org.springframework.data.jpa.domain.Specification;

public class AutorFilter {

    public static Specification<Autor> getAutorSpecification(
            String name,
            String surname1,
            String surname2,
            AutorType autorType
    ) {

        SpecificationsBuilder<Autor> spec = new SpecificationsBuilder<>();

        if (name != null) {
            spec.with(AutorSpecification.NAME, name, false);
        }

        if (surname1 != null) {
            spec.with(AutorSpecification.SURNAME1, surname1, false);
        }

        if (surname2 != null) {
            spec.with(AutorSpecification.SURNAME2, surname2, false);
        }

        if (autorType != null) {
            spec.with(AutorSpecification.AUTOR_TYPE, autorType, false);
        }

        return Specification.where(spec.build(AutorSpecification::new));
    }

}
