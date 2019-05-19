package ch.bfh.red.backend.services;

import ch.bfh.red.backend.repositories.ExpositionNoteRepository;
import ch.bfh.red.backend.models.ExpositionNote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("expositionNoteService")
public class ExpositionNoteService {
    private ExpositionNoteRepository expositionNoteRepository;

    public ExpositionNoteService(@Autowired ExpositionNoteRepository expositionNoteRepository) {
        this.expositionNoteRepository = expositionNoteRepository;
    }

    public List<ExpositionNote> getExpositionNotes() {
        return expositionNoteRepository.findAll();
    }
}