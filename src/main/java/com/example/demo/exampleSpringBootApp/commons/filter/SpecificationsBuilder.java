package com.example.demo.exampleSpringBootApp.commons.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpecificationsBuilder<T> {

    private final List<SearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationsBuilder with(String key, Object value, boolean orPredicate) {
        params.add(new SearchCriteria(key, value, orPredicate));
        return this;
    }

    public Specification<T> build(Function<SearchCriteria, Specification<T>> converter) {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(converter)
                .collect(Collectors.toList());

        Specification<T> result = specs.get(0);

        for (int idx = 1; idx < specs.size(); idx++) {
            result = params.get(idx)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(idx))
                    : Specification.where(result)
                    .and(specs.get(idx));
        }

        return result;
    }
}
