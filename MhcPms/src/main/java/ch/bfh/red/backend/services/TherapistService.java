package ch.bfh.red.backend.services;

import ch.bfh.red.backend.repositories.TherapistRepository;
//import ch.bfh.red.backend.models.SearchTherapist;
import ch.bfh.red.backend.models.Therapist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("therapistService")
public class TherapistService {
    private TherapistRepository therapistRepository;

    /*public TherapistService(@Autowired TherapistRepository therapistRepository) {
        this.therapistRepository = therapistRepository;
    }*/

    /*public List<Therapist> getTherapists() {
        return therapistRepository.findAll();
    }*/

//    public List<Therapist> search(SearchTherapist search) {
//        return getTherapists().stream().map(t -> t.search(search)).flatMap(Collection::stream).collect(Collectors.toList());
//    }
//
//    public Therapist add(Therapist therapist) {
//        return this.therapistRepository.saveAndFlush(therapist);
//    }
//
//    public Therapist save(Therapist therapist) throws Exception {
////        if (therapist.getId() == null) {
////            return therapistRepository.save(therapist);
////        }
//        Therapist daoTherapist = therapistRepository.findById(therapist.getId()).orElseThrow(() -> new Exception("Therapist not found"));
//        return therapistRepository.save(daoTherapist);
//    }
//
//    public void delete(Therapist therapist) {
//        therapistRepository.delete(therapist);
//    }
}
