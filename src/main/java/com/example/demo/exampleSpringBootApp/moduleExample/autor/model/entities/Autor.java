package com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities;

import com.example.demo.exampleSpringBootApp.domain.enums.AutorType;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "autors")

/*
* SOFT DELETE
* Con @SQLDelete indicamos que al eliminar haga algo, en este caso modificar el
* atributo deleted a true
*/
@SQLDelete(sql = "UPDATE autors SET deleted = true WHERE id=?")

/*
* Si usamos  @Where nunca devolverá las entidades con atributo deleted=true, NUNCA!!!
* Si usamos @Filter, decidimos en el controlador
*/
//@Where(clause = "deleted=false")
//@FilterDef(name = "softDeleted", parameters = @ParamDef(name = "isDeleted", type = "boolean"),defaultCondition = "deleted = 'false'")
//@Filter(name = "softDeleted", condition = "deleted = :isDeleted")
@FilterDef(name = "softDeleted", defaultCondition = "deleted = false")
@Filter(name = "softDeleted")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname1")
    private String surname1;
    @Column(name = "surname2")
    private String surname2;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="autor_type")
    private AutorType autorType;


    @Column(name="deleted")
    private boolean deleted;


    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToMany(mappedBy = "autors", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Tutorial> tutorials;


    public Autor() {
        super();
        this.tutorials = new HashSet<>();
    }

    public Autor(String name, String surname1, String surname2, AutorType autorType, boolean deleted) {
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.autorType = autorType;
        this.tutorials = new HashSet<>();
        this.deleted = deleted;
    }

}
