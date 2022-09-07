package com.example.demo.exampleSpringBootApp.devops.population;

import com.example.demo.exampleSpringBootApp.domain.enums.AutorType;
import com.example.demo.exampleSpringBootApp.domain.enums.TutorialType;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.AutorRepository;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.TutorialRepository;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PopulationService {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    TutorialRepository tutorialRepository;

    @Transactional
    public void populateDatabase() {
        this.populateAutors();
        this.populateTutorials();
    }

    private void populateAutors() {
        List<Autor> autors = new ArrayList<>();

        for (int i=1; i <= 7; i++) {
            Autor t = new Autor();

            t.setName("Name " + i);
            t.setSurname1("Surname1 " + i);
            t.setSurname2( "Surname2 " + i );
            t.setAutorType( AutorType.values()[ this.getRandomNumberBetweenMinMax(1,AutorType.values().length) - 1 ] );
            autors.add(t);
        }

        autorRepository.saveAll(autors);

    }

    private void populateTutorials() {
        List<Autor> autorsPersist;
        autorsPersist = autorRepository.findAll();

//        List<Tutorial> tutorials = new ArrayList<>();

        for (int i=1; i <= 6; i++) {
            Tutorial t = new Tutorial();

            t.setTitle("Title " + i);
            t.setDescription("Descripción " + i);
            t.setPublished( this.getRandomBoolean() );
            t.setTutorialType( TutorialType.values()[ this.getRandomNumberBetweenMinMax(1,TutorialType.values().length) - 1 ] );

            int numAutors = this.getRandomNumberBetweenMinMax(1, 3);
            for (int j=0; j < numAutors; j++) {

//                List<Autor> autorsPersist;
//                autorsPersist = autorRepository.findAll();

                Autor a = autorsPersist.get(
                        this.getRandomNumberBetweenMinMax(1, autorsPersist.size()) - 1
                );
//                a.setTutorial(t);
//                a.getTutorials().add(t);
//                if (!t.getAutors().contains(a))
                t.getAutors().add(a);
            }
            tutorialRepository.saveAndFlush(t);
//            tutorials.add(t);
        }

//        tutorialRepository.saveAll(tutorials);

    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private int getRandomNumberBetweenMinMax(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
