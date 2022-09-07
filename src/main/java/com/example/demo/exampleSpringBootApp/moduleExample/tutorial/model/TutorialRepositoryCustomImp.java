package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model;

import com.example.demo.exampleSpringBootApp.commons.repository.BaseRepository;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class TutorialRepositoryCustomImp extends BaseRepository implements TutorialRepositoryCustom{

    @Override
    public Page<Tutorial> findAllTutorialDTOList(Specification<Tutorial> spec, Pageable page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tutorial> cq = cb.createQuery(Tutorial.class);
        Root<Tutorial> root = cq.from(Tutorial.class);

//        cq.select(cb.construct(TutorialDTO.class,
//                root.get("id"),
//                root.get("title"),
//                root.get("description"),
//                root.get("published"),
//                root.get("tutorialType"),
//                cb.construct(AutorDTO.class,
//                        autor.get("id"),
//                        autor.get("name"),
//                        autor.get("surname1"),
//                        autor.get("surname2"),
//                        autor.get("autorType")
//               )
//        ));

        cq.select(root);
        Predicate p = spec.toPredicate(root, cq, cb);
        if (p != null)
            cq.where(p);

        Sort s = Sort.by("id");
        cq.orderBy(QueryUtils.toOrders(page.getSortOr(s), root, cb));

        TypedQuery<Tutorial> q = em.createQuery(cq);
        q.setMaxResults(page.getPageSize()).setFirstResult((int) page.getOffset());

        return new PageImpl<>(q.getResultList(), page, count(em,root,cq));


    }

}
