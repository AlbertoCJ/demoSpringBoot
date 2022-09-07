package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.filters;

import com.example.demo.exampleSpringBootApp.commons.filter.SearchCriteria;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class TutorialSpecification implements Specification<Tutorial> {

    /* Nombre param */
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PUBLISHED = "published";
    public static final String AUTOR_NAME = "autorName";
//    public static final String DATE_START = "dateStart";
//    public static final String DATE_END = "dateEnd";

    /* Nombres en BD */
    private final String DB_TITLE = TITLE;
    private final String DB_DESCRIPTION = DESCRIPTION;
    private final String DB_PUBLISHED = PUBLISHED;
    private final String DB_AUTOR_NAME = "name";
//    public final String DB_DATE_CREATION = "date_creation";

    private SearchCriteria criteria;

    public TutorialSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Tutorial> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {


        Predicate predicate;

        switch(criteria.getKey())
        {
            case TITLE :
                predicate = cb.like(root.get(DB_TITLE), "%"+criteria.getValue()+"%");
                break;
            case DESCRIPTION :
                predicate = cb.like(root.get(DB_DESCRIPTION), "%"+criteria.getValue()+"%");
                break;
            case PUBLISHED :
                predicate = cb.equal(root.get(DB_PUBLISHED), criteria.getValue());
                break;
            case AUTOR_NAME :
                Join<Tutorial, Autor> joinAutor = root.join("autors", JoinType.LEFT);
                predicate = cb.like(joinAutor.get(DB_AUTOR_NAME), "%"+criteria.getValue()+"%");
                break;
//            case DATE_START :
//                predicate = cb.greaterThanOrEqualTo(root.get(DB_DATE_CREATION), (String)criteria.getValue());
//                break;
//            case DATE_END :
//                predicate = cb.lessThanOrEqualTo(root.get(DB_DATE_CREATION), (String)criteria.getValue());
//                break;
            default:
                predicate = null;
        }

        return predicate;
    }

}
