package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.views.EditTherapyView;
import ch.bfh.red.ui.views.ListTherapyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TherapyPresenter implements ListTherapyView.ListViewListener, EditTherapyView.DetailViewListener {
    private final TherapyService therapyService;
    private final PatientService patientService;
    private final TherapistService therapistService;
    private ListTherapyView listTherapyView;
    private EditTherapyView editTherapyView;
    private List<Therapy> therapies = new ArrayList<>();

    private Therapy loadedTherapy;

    @Autowired
    public TherapyPresenter(TherapyService therapyService, TherapistService therapistService, PatientService patientService) {
        this.therapyService = therapyService;
        this.patientService = patientService;
        this.therapistService = therapistService;
    }

    public void setView(ListTherapyView listTherapyView) {
        this.listTherapyView = listTherapyView;
        listTherapyView.setListener(this);
        updateList(false, null, null, null);

        List<Patient> patients = therapies.stream().map(Therapy::getPatient).distinct().sorted().collect(Collectors.toList());
        listTherapyView.setPatients(patients);
    }

    public void setView(EditTherapyView editTherapyView) {
        this.editTherapyView = editTherapyView;
        editTherapyView.setListener(this);

        List<Patient> patients = patientService.getAll();
        editTherapyView.setPatients(patients);

        List<Therapist> therapists = therapistService.getAll();
        editTherapyView.setTherapists(therapists);
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
        return therapyService;
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

    public void addMockData() {
        Patient patient = new Patient("Jürgen", "Test", new Address("Langstrasse", "12k", 7777, "Burgdorf"));
        Therapy therapy = new Therapy(new Date(), TherapyType.ART);
        Therapist therapist = new Therapist("user", "1234", AcademicTitle.DOCTOR, "Ueli", "Kramer", new Address("Burgstrasse", "18", 3600, "Thun"));

        therapy.setTherapist(therapist);
        therapy.setPatient(patient);

        SingleSession singleSession = new SingleSession(patient, therapist, new Date(), new Date(), SessionType.DISCUSSION);
        List<SingleSession> singleSessions = new ArrayList<>();
        singleSessions.add(singleSession);
        therapy.setSingleSessions(singleSessions);

        List<GroupSession> groupSessions = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        List<Therapist> therapists = new ArrayList<>();
        therapists.add(therapist);
        groupSessions.add(new GroupSession(patients, therapists, therapist, new Date(), new Date(), SessionType.DISCUSSION));
        therapy.setGroupSessions(groupSessions);
        PatientNote note = new PatientNote(patient, new Date(), "Dieser Text", Visibility.PUBLIC);
        List<PatientNote> notes = new ArrayList<>();
        notes.add(note);
        therapy.setPatientNotes(notes);
        therapyService.persist(therapy);
    }
}
