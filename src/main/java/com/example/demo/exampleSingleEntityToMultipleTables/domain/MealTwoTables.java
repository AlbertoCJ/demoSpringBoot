package com.example.demo.exampleSingleEntityToMultipleTables.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "mealTable1")
/* Definimos la tabla secundaria */
@SecondaryTable(name = "allergensTable2", pkJoinColumns = @PrimaryKeyJoinColumn(name = "mealTable1_id"))
@Getter
@Setter
class MealTwoTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "price")
    BigDecimal price;

    /* Definimos el nombre de la colum y la tabla secundaria */
    @Column(name = "peanuts", table = "allergensTable2")
    boolean peanuts;

    @Column(name = "celery", table = "allergensTable2")
    boolean celery;

    @Column(name = "sesame_seeds", table = "allergensTable2")
    boolean sesameSeeds;

}
