package com.example.demo.exampleSpringBootApp.moduleExample.autor;

import com.example.demo.exampleSpringBootApp.commons.repository.BaseRepository;
import com.example.demo.exampleSpringBootApp.domain.mappers.AutorMapper;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.AutorRepository;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.dto.AutorDTO;
import com.example.demo.exampleSpringBootApp.moduleExample.autor.model.entities.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AutorService extends BaseRepository {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    AutorMapper autorMapper;

    public Page<AutorDTO> getFilterAutors(Specification<Autor> spec, Pageable page, boolean filterSoftDeleted) {
        if (filterSoftDeleted) this.enableFilterSoftDeleted();
        Page<AutorDTO> autors = autorRepository.findAll(spec, page).map(t -> autorMapper.toAutorDTO((Autor)t));
        if (filterSoftDeleted) this.disableFilterSoftDeleted();
        return autors;

    }

    public AutorDTO getAutorById(long id, boolean filterSoftDeleted) {
        Autor autor = autorRepository.getReferenceById(id);

        if (filterSoftDeleted && autor.isDeleted())
            throw new EntityNotFoundException();

        return autorMapper.toAutorDTO(autor);
    }

    public AutorDTO createAutor(AutorDTO autorDTO) {

        Autor a = autorMapper.createAutor(autorDTO);
        a = autorRepository.save(a);
        return autorMapper.toAutorDTO(a);
    }

    public AutorDTO updateAutor(long id,AutorDTO autorDTO) {

        Autor a = autorRepository.getReferenceById(id);

        if (a.isDeleted())
            throw new EntityNotFoundException();

        autorMapper.updateAutor(autorDTO, a);
        a = autorRepository.save(a);
        return autorMapper.toAutorDTO(a);
    }

    public void deleteAutorById(long id) {
        autorRepository.deleteById(id);
    }

    public void deleteAllAutors() {
        autorRepository.deleteAll();
    }

}
