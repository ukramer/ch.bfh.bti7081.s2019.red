package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.persistence.PatientPersistenceManager;
import ch.bfh.red.backend.persistence.TherapistPersistenceManager;
import ch.bfh.red.backend.persistence.TherapyPersistenceManager;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.views.EditTherapyView;
import ch.bfh.red.ui.views.ListTherapyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TherapyPresenter implements ListTherapyView.ListViewListener, EditTherapyView.DetailViewListener {

    @Autowired
    private TherapyPersistenceManager therapyManager;

    @Autowired
    private PatientPersistenceManager patientManager;

    @Autowired
    private TherapistPersistenceManager therapistManager;

    private ListTherapyView listTherapyView;
    private EditTherapyView editTherapyView;
    private List<Therapy> therapies = new ArrayList<>();

    private Therapy loadedTherapy;

    public void setView(ListTherapyView listTherapyView) {
        this.listTherapyView = listTherapyView;
        listTherapyView.setListener(this);
        updateList(false, null, null, null);

        List<Patient> patients = therapies.stream().map(Therapy::getPatient).distinct().sorted(Comparator.comparing(AbstractPerson::getLastName)).collect(Collectors.toList());
        listTherapyView.setPatients(patients);
    }

    public void setView(EditTherapyView editTherapyView) {
        this.editTherapyView = editTherapyView;
        editTherapyView.setListener(this);

        List<Patient> patients = patientManager.getService().getAll();
        patients.sort(Comparator.comparing(AbstractPerson::getLastName));
        editTherapyView.setPatients(patients);

        List<Therapist> therapists = therapistManager.getService().getAll();
        therapists.sort(Comparator.comparing(AbstractPerson::getLastName));
        editTherapyView.setTherapists(therapists);

        List<TherapyType> therapyTypes = Arrays.asList(TherapyType.values());
        editTherapyView.setTherapyTypes(therapyTypes);
    }

    public void delete(Therapy therapy) {
        getService().delete(getService().getById(therapy.getId()));
    }

    @Override
    public void load(Integer therapyId) {
        loadedTherapy = getService().getByIdWithAllAssociations(therapyId);

        editTherapyView.setTherapy(loadedTherapy);

        editTherapyView.setSingleSessions(loadedTherapy.getSingleSessions());
        editTherapyView.setGroupSessions(loadedTherapy.getGroupSessions());

        editTherapyView.setPatientNotes(loadedTherapy.getPatientNotes());
        editTherapyView.setTherapistNotes(loadedTherapy.getTherapistNotes());
    }

    @Override
    public void save(Therapy therapy) throws Exception {
        if (therapy.getStartDate() == null) {
            throw new Exception("Es muss ein Startdatum gesetzt sein. Die Therapie wurde nicht aktualisiert");
        }
        getService().persist(therapy);
    }

    @Override
    public void prepareNewObject() {
        editTherapyView.setTherapy(new Therapy());
    }

    public TherapyService getService() {
        return (TherapyService) therapyManager.getService();
    }

    @Override
    public void updateList(boolean finished, Patient patient, LocalDate start, LocalDate end) {
        therapies.clear();
        if (patient != null) {
            if (start != null && end != null) {
                therapies = getService().getByFinishedAndPatientNameAndDateRange(finished, patient.getFirstName(), patient.getLastName(), start.toString(), end.toString());
            } else if (start != null) {
                therapies = getService().getByFinishedAndPatientNameAndStartDate(finished, patient.getFirstName(), patient.getLastName(), start.toString());
            } else if (end != null) {
                therapies = getService().getByFinishedAndPatientNameAndEndDate(finished, patient.getFirstName(), patient.getLastName(), end.toString());
            } else {
                therapies = getService().getByFinishedAndPatientName(finished, patient.getFirstName(), patient.getLastName());
            }
        } else {
            if (start != null && end != null) {
                therapies = getService().getByFinishedAndDateRange(finished, start.toString(), end.toString());
            } else if (start != null) {
                therapies = getService().getByFinishedAndStartDate(finished, start.toString());
            } else if (end != null) {
                therapies = getService().getByFinishedAndEndDate(finished, end.toString());
            } else {
                therapies = getService().getByFinished(finished);
            }
        }
        listTherapyView.setTherapies(therapies);
    }
}