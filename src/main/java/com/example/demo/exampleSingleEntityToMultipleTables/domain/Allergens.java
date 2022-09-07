package com.example.demo.exampleSingleEntityToMultipleTables.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
class Allergens {

    @Column(name = "peanuts", table = "allergens")
    boolean peanuts;

    @Column(name = "celery", table = "allergens")
    boolean celery;

    @Column(name = "sesame_seeds", table = "allergens")
    boolean sesameSeeds;

}