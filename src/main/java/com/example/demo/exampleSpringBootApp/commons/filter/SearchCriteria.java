package com.example.demo.exampleSpringBootApp.commons.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
    /*
        key : el nombre del campo, por ejemplo, firstName , age , etc.
        operación : la operación, por ejemplo, igualdad, menor que, etc.
        value : el valor del campo, por ejemplo, john, 25, etc.
        orPredicate: false -> si es and, true -> si es or
     */
    private String key;
//    private String operation;
    private Object value;
    private boolean orPredicate;
}
