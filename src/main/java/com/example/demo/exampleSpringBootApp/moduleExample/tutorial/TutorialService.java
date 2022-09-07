package com.example.demo.exampleSpringBootApp.moduleExample.tutorial;

import com.example.demo.exampleSpringBootApp.domain.mappers.TutorialMappper;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.TutorialRepository;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.TutorialRepositoryCustom;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto.TutorialListDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto.TutorialSaveDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.entities.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    TutorialRepositoryCustom tutorialRepositoryCustom;

    @Autowired
    TutorialMappper tutorialMappper;

    @Transactional
    public Page<TutorialListDTO> getFilterTutorials(Specification<Tutorial> spec, Pageable page) {
        /* Usando un repository custom */
        return tutorialRepositoryCustom.findAllTutorialDTOList(spec, page)
                .map(t -> tutorialMappper.toTutorialListDTO(t));

        /* Usando JpaRepository */
//        return tutorialRepository.findAll(spec, page)
//                .map(t -> tutorialMappper.toTutorialDTO((Tutorial)t));
    }

    public TutorialListDTO getTutorialById(long id) {

        Tutorial t = tutorialRepository.getReferenceById(id);

        return tutorialMappper.toTutorialListDTO(t);

    }

    @Transactional
    public TutorialListDTO createTutorial(TutorialSaveDTO tutorialSaveDTO) {

        Tutorial t = tutorialMappper.createTutorial(tutorialSaveDTO);

        t.getAutors().forEach(a -> {
            if (a.isDeleted())
                throw new EntityNotFoundException("Entidad no encontrada con id: "+ a.getId());
        });

        t = tutorialRepository.save(t);
        return tutorialMappper.toTutorialListDTO(t);
    }

    @Transactional
    public TutorialListDTO updateTutorial(long id, TutorialSaveDTO tutorialSaveDTO) {

        Tutorial t = tutorialRepository.getReferenceById(id);
        tutorialMappper.updateTutorial(tutorialSaveDTO, t);
        t = tutorialRepository.save(t);
        return tutorialMappper.toTutorialListDTO(t);
    }

    public void deleteTutorialById(long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }


}
