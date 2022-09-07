package com.example.demo.exampleSpringBootApp.moduleExample.autor;

import com.example.demo.exampleSpringBootApp.commons.validation.GroupValidationCRUD;
import com.example.demo.exampleSpringBootApp.domain.enums.AutorType;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.filters.AutorFilter;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.dto.AutorDTO;
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

@RestController
@Validated
@RequestMapping("/api")
public class AutorController {

    @Autowired
    AutorService autorService;

    @GetMapping("/autors")
    public ResponseEntity<Page<AutorDTO>> getAllAutors(
            Pageable page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname1,
            @RequestParam(required = false) String surname2,
            @RequestParam(required = false) AutorType autorType
    ) {
        Page<AutorDTO> autorsDTO = autorService.getFilterAutors(
                AutorFilter.getAutorSpecification(name, surname1, surname2, autorType),
                page,
                true
        );

        if (autorsDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autorsDTO, HttpStatus.OK);
    }

    @GetMapping("/autors/{id}")
    public ResponseEntity<AutorDTO> getAutorById(@PathVariable("id") long id) {
        return new ResponseEntity<>(autorService.getAutorById(id, true), HttpStatus.OK);
    }

    @PostMapping("/autors")
    public ResponseEntity<AutorDTO> createAutor(
            @Validated(GroupValidationCRUD.Create.class) @RequestBody AutorDTO autorDTO
    ) {

        return new ResponseEntity<>(
                autorService.createAutor(autorDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/autors/{id}")
    public ResponseEntity<AutorDTO> updateAutor(
            @PathVariable("id") @Min(message = "Id debe ser mayor a 0", value = 1) long id,
            @Validated(GroupValidationCRUD.Update.class) @RequestBody AutorDTO autorDTO
    ) {
        return new ResponseEntity<>(
                autorService.updateAutor(id, autorDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/autors/{id}")
    public ResponseEntity<HttpStatus> deleteAutor(@PathVariable("id") long id) {
        autorService.deleteAutorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/autors")
    public ResponseEntity<HttpStatus> deleteAllAutors() {
        autorService.deleteAllAutors();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
