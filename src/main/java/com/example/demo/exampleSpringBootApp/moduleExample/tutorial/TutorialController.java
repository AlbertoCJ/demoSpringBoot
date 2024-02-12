package com.example.demo.exampleSpringBootApp.moduleExample.tutorial;

import com.example.demo.exampleSpringBootApp.commons.validation.GroupValidationCRUD;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.filters.TutorialFilter;

import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto.TutorialListDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto.TutorialSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;


//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Validated
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialService tutorialService;

	@GetMapping("/tutorials")
	public ResponseEntity<Page<TutorialListDTO>> getAllTutorials(
			Pageable page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String desc,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) String autorName
	) {
		Page<TutorialListDTO> tutorialsDTO = tutorialService.getFilterTutorials(
			TutorialFilter.init().addTitle(title).addDescription(desc).addPublished(published).addAutorName(autorName).getTutorialSpecification(),
			page
		);

		if (tutorialsDTO.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorialsDTO, HttpStatus.OK);
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<TutorialListDTO> getTutorialById(@PathVariable("id") long id) {
		return new ResponseEntity<>(tutorialService.getTutorialById(id), HttpStatus.OK);
	}

	@PostMapping("/tutorials")
	public ResponseEntity<TutorialListDTO> createTutorial(
			@Validated(GroupValidationCRUD.Create.class) @RequestBody TutorialSaveDTO tutorialSaveDTO
	) {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		Validator validator = factory.getValidator();
////		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Set<ConstraintViolation<TutorialDTO>> violations = validator.validate(tutorialDTO);
//		if (!violations.isEmpty()) {
//			throw new ConstraintViolationException(violations);
//		}

		return new ResponseEntity<>(
				tutorialService.createTutorial(tutorialSaveDTO),
				HttpStatus.CREATED
		);
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<TutorialListDTO> updateTutorial(
			@PathVariable("id") @Min(message = "Id debe ser mayor a 0", value = 1) long id,
			@Validated(GroupValidationCRUD.Update.class) @RequestBody TutorialSaveDTO tutorialSaveDTO
	) {
		return new ResponseEntity<>(
				tutorialService.updateTutorial(id, tutorialSaveDTO),
				HttpStatus.OK
		);
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		tutorialService.deleteTutorialById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		tutorialService.deleteAllTutorials();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
