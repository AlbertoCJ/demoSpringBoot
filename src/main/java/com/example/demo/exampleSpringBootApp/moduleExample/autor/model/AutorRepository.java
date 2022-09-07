package com.example.demo.exampleSpringBootApp.moduleExample.autor.model;

import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AutorRepository
        extends JpaRepository<Autor, Long>,
        PagingAndSortingRepository<Autor, Long>,
        JpaSpecificationExecutor<Autor>{
}
