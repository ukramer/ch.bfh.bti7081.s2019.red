package ch.bfh.red.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.bfh.red.backend.models.Therapy;

import java.util.Date;
import java.util.List;

@Repository("therapyRepository")
public interface TherapyRepository extends CrudRepository<Therapy, Integer> {
    List<Therapy> findByFinished(boolean finished);
    List<Therapy> findByFinishedAndPatientFirstNameAndPatientLastName(boolean finished, String firstName, String lastName);

    @Query(value = "SELECT t FROM Therapy t WHERE finished = ?1 AND patient.firstName = ?2 AND patient.lastName = ?3 AND startDate >= ?4 AND startDate <= ?5")
    List<Therapy> findByFinishedAndPatientFirstNameAnPatientLastNameAndStartAndEndDate(boolean finished, String firstName, String lastName, Date start, Date end);

    @Query(value = "SELECT t FROM Therapy t WHERE finished = ?1 AND patient.firstName = ?2 AND patient.lastName = ?3 AND startDate >= ?4")
    List<Therapy> findByFinishedAndPatientFirstNameAnPatientLastNameAndStartDate(boolean finished, String firstName, String lastName, Date start);

    @Query(value = "SELECT t FROM Therapy t WHERE finished = ?1 AND patient.firstName = ?2 AND patient.lastName = ?3 AND startDate <= ?4")
    List<Therapy> findByFinishedAndPatientFirstNameAnPatientLastNameAndEndDate(boolean finished, String firstName, String lastName, Date end);

    @Query(value = "SELECT t FROM Therapy t WHERE finished = ?1 AND startDate >= ?2 AND startDate <= ?3")
    List<Therapy> findByFinishedAndStartAndEndDate(boolean finished, Date start, Date end);

    @Query(value = "SELECT t FROM Therapy t WHERE finished = ?1 AND startDate >= ?2")
    List<Therapy> findByFinishedAndStartDate(boolean finished, Date start);

    @Query(value = "SELECT t FROM Therapy t WHERE finished = ?1 AND startDate <= ?2")
    List<Therapy> findByFinishedAndEndDate(boolean finished, Date end);
}
