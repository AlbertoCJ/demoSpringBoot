package com.example.demo.exampleSpringBootApp.moduleExample.autor.filters;

import com.example.demo.exampleSpringBootApp.commons.filter.SearchCriteria;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class AutorSpecification implements Specification<Autor> {

    /* Nombre param */
    public static final String NAME = "name";
    public static final String SURNAME1 = "surname1";
    public static final String SURNAME2 = "surname2";
    public static final String AUTOR_TYPE = "autorType";

    /* Nombres en BD */
    private final String DB_NAME = NAME;
    private final String DB_SURNAME1 = SURNAME1;
    private final String DB_SURNAME2 = SURNAME2;
    private final String DB_AUTOR_TYPE = AUTOR_TYPE; // "autor_type";

    private SearchCriteria criteria;

    public AutorSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Autor> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {


        Predicate predicate;

        switch(criteria.getKey())
        {
            case NAME:
                predicate = cb.like(root.get(DB_NAME), "%"+criteria.getValue()+"%");
                break;
            case SURNAME1:
                predicate = cb.like(root.get(DB_SURNAME1), "%"+criteria.getValue()+"%");
                break;
            case SURNAME2:
                predicate = cb.like(root.get(DB_SURNAME2), "%"+criteria.getValue()+"%");
                break;
            case AUTOR_TYPE:
                predicate = cb.equal(root.get(DB_AUTOR_TYPE), criteria.getValue());
                break;
            default:
                predicate = null;
        }

        return predicate;
    }

}
