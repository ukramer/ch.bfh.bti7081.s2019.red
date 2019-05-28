package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.views.Therapy.DetailView;
import ch.bfh.red.ui.views.Therapy.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TherapyPresenter implements ListView.ListViewListener, DetailView.DetailViewListener {
    private ListView listView;
    private DetailView detailView;

    private final TherapyService therapyService;
    private final PatientService patientService;
    private final TherapistService therapistService;

    private List<Therapy> therapies = new ArrayList<>();

    private Therapy loadedTherapy;

    @Autowired
    public TherapyPresenter(TherapyService therapyService, PatientService patientService, TherapistService therapistService) {
        this.therapyService = therapyService;
        this.patientService = patientService;
        this.therapistService = therapistService;
    }

    public void setView(ListView listView) {
        this.listView = listView;
        listView.setListener(this);
        updateList(false, null, null, null);

        List<Patient> patients = therapies.stream().map(Therapy::getPatient).distinct().sorted().collect(Collectors.toList());
        listView.setPatients(patients);
    }

    public void setView(DetailView detailView) {
        this.detailView = detailView;
        detailView.setListener(this);

        List<Patient> patients = patientService.getAll();
        detailView.setPatients(patients);

        List<Therapist> therapists = therapistService.getAll();
        detailView.setTherapists(therapists);
    }

    public void delete(Therapy therapy) {
        getService().delete(getService().getById(therapy.getId()));
    }

    @Override
    public void load(Integer therapyId) {
        loadedTherapy = getService().getByIdWithAllAssociations(therapyId);

        detailView.setTherapy(loadedTherapy);

        detailView.setSingleSessions(loadedTherapy.getSingleSessions());
        detailView.setGroupSessions(loadedTherapy.getGroupSessions());

        detailView.setPatientNotes(loadedTherapy.getPatientNotes());
        detailView.setTherapistNotes(loadedTherapy.getTherapistNotes());
    }

    @Override
    public void save(Therapy therapy) throws Exception {
        if (therapy.getStartDate() == null) {
            throw new Exception("Es muss ein Startdatum gesetzt sein. Die Therapie wurde nicht aktualisiert");
        }
        getService().update(therapy);
    }

    @Override
    public void prepareNewObject() {
        detailView.setTherapy(new Therapy());
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
        listView.setTherapies(therapies);
    }
}
