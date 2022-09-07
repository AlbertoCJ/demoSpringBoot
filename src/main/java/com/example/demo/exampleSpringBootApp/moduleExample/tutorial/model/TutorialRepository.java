package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model;

import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TutorialRepository
        extends JpaRepository<Tutorial, Long>,
        PagingAndSortingRepository<Tutorial, Long>,
        JpaSpecificationExecutor<Tutorial>{

}
