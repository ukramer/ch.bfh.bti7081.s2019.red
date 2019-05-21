package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.views.Therapy.DetailView;
import ch.bfh.red.ui.views.Therapy.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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

    private List<Therapy> therapies = new ArrayList<>();

    private Therapy loadedTherapy;

    @Autowired
    public TherapyPresenter(TherapyService therapyService) {
        this.therapyService = therapyService;
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
    }

    public void delete(Therapy therapy) {
        getService().delete(getService().getById(therapy.getId()));
    }

    @Override
    public void load(Integer therapyId) {
        loadedTherapy = getService().getById(therapyId);
        detailView.setTherapy(loadedTherapy);
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

    public void addMockData() {
        Patient patient = new Patient("Jürgen", "Test", new Address("Langstrasse", "12k", 7777, "Burgdorf"));
        Therapy therapy = new Therapy(new Date(), new TherapyType("Exposition", ""));
        therapy.setTherapist(new Therapist("user", "1234", new AcademicTitle("Dr.", ""), "Ueli", "Kramer", new Address("Burgstrasse", "18", 3600, "Thun")));
        therapy.setPatient(patient);
        therapyService.add(therapy);

        Date date = new Date();
        try {
            date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2018-01-01");
        } catch (Exception e) {
        }
        Patient patient2 = new Patient("Jürgen", "Test", new Address("Langstrasse", "12k", 7777, "Burgdorf"));
        Therapy therapy2 = new Therapy(date, new TherapyType("Exposition", ""));
        therapy2.setTherapist(new Therapist("user", "1234", new AcademicTitle("Dr.", ""), "Ueli", "Kramer", new Address("Burgstrasse", "18", 3600, "Thun")));
        therapy2.setPatient(patient2);
        therapyService.add(therapy2);
    }
}
