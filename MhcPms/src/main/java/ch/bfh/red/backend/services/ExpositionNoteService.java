package ch.bfh.red.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.repositories.ExpositionNoteRepository;

@Service("expositionNoteService")
public class ExpositionNoteService implements IService<ExpositionNote> {

    @Autowired
    private ExpositionNoteRepository repository;

    @Override
    public CrudRepository<ExpositionNote, Integer> getRepository() {
        return repository;
    }

}