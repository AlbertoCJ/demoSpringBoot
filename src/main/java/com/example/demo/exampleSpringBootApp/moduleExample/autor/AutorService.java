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
import javax.transaction.Transactional;

@Service
public class AutorService extends BaseRepository {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    AutorMapper autorMapper;

    @Transactional
    public Page<AutorDTO> getFilterAutors(Specification<Autor> spec, Pageable page, boolean filterSoftDeleted) {
        if (filterSoftDeleted) this.enableFilterSoftDeleted();
        Page<AutorDTO> autors = autorRepository.findAll(spec, page).map(t -> autorMapper.toAutorDTO((Autor)t));
        if (filterSoftDeleted) this.disableFilterSoftDeleted();
        return autors;

    }

    @Transactional
    public AutorDTO getAutorById(long id, boolean filterSoftDeleted) {
        Autor autor = autorRepository.getReferenceById(id);

        if (filterSoftDeleted && autor.isDeleted())
            throw new EntityNotFoundException();

        return autorMapper.toAutorDTO(autor);
    }

    @Transactional
    public AutorDTO createAutor(AutorDTO autorDTO) {

        Autor a = autorMapper.createAutor(autorDTO);
        a = autorRepository.save(a);
        return autorMapper.toAutorDTO(a);
    }

    @Transactional
    public AutorDTO updateAutor(long id,AutorDTO autorDTO) {

        Autor a = autorRepository.getReferenceById(id);

        if (a.isDeleted())
            throw new EntityNotFoundException();

        autorMapper.updateAutor(autorDTO, a);
        a = autorRepository.save(a);
        return autorMapper.toAutorDTO(a);
    }

    @Transactional
    public void deleteAutorById(long id) {
        autorRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllAutors() {
        autorRepository.deleteAll();
    }

}
