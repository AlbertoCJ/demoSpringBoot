package com.example.demo.exampleSpringBootApp.moduleExample.autor.filters;

import com.example.demo.exampleSpringBootApp.commons.filter.SpecificationsBuilder;
import com.example.demo.exampleSpringBootApp.domain.enums.AutorType;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import org.springframework.data.jpa.domain.Specification;

public class AutorFilter {

	private SpecificationsBuilder<Autor> spec = new SpecificationsBuilder<>();

    public static AutorFilter init() {
    	return new AutorFilter();
    }




    public AutorFilter addName(String name) {
        if (name != null) {
            spec.with(AutorSpecification.NAME, name, false);
        }
        return this;
    }

    public AutorFilter addSurname1(String surname1) {
        if (surname1 != null) {
            spec.with(AutorSpecification.SURNAME1, surname1, false);
        }
        return this;
    }

    public AutorFilter addSurname2(String surname2) {
        if (surname2 != null) {
            spec.with(AutorSpecification.SURNAME2, surname2, false);
        }
        return this;
    }

    public AutorFilter addAutorType(AutorType autorType) {
    	if (autorType != null) {
    		spec.with(AutorSpecification.AUTOR_TYPE, autorType, false);
    	}
    	return this;
    }




    public Specification<Autor> getAutorSpecification() {
        return Specification.where(spec.build(AutorSpecification::new));
    }
	
//    public static Specification<Autor> getAutorSpecification(
//            String name,
//            String surname1,
//            String surname2,
//            AutorType autorType
//    ) {
//
//        SpecificationsBuilder<Autor> spec = new SpecificationsBuilder<>();
//
//        if (name != null) {
//            spec.with(AutorSpecification.NAME, name, false);
//        }
//
//        if (surname1 != null) {
//            spec.with(AutorSpecification.SURNAME1, surname1, false);
//        }
//
//        if (surname2 != null) {
//            spec.with(AutorSpecification.SURNAME2, surname2, false);
//        }
//
//        if (autorType != null) {
//            spec.with(AutorSpecification.AUTOR_TYPE, autorType, false);
//        }
//
//        return Specification.where(spec.build(AutorSpecification::new));
//    }

}
