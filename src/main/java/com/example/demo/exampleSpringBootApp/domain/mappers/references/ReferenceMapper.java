package com.example.demo.exampleSpringBootApp.domain.mappers.references;

import lombok.NonNull;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ReferenceMapper {

    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(@NonNull final Long id, @TargetType Class<T> type) {
        return entityManager.getReference(type, id);
    }

}
