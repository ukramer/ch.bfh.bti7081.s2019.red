package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.views.Therapy.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TherapyPresenter implements ListView.ListViewListener {
    private ListView listView;
    private TherapyService therapyService;
    private List<Therapy> therapies = new ArrayList<>();

    public TherapyPresenter(ListView listView, TherapyService therapyService) {
        this.listView = listView;
        this.therapyService = therapyService;
        listView.addListener(this);

        updateList(false, null, null, null);

        List<Patient> patients = therapies.stream().map(Therapy::getPatient).distinct().sorted().collect(Collectors.toList());
        listView.setPatients(patients);
    }

    public void delete(Therapy therapy) {
        therapyService.delete(therapyService.getById(therapy.getId()));
    }

    @Override
    public void updateList(boolean finished, Patient patient, LocalDate start, LocalDate end) {
        therapies.clear();
        if (patient != null) {
            if (start != null && end != null) {
                therapies = therapyService.getByFinishedAndPatientNameAndDateRange(finished, patient.getFirstName(), patient.getLastName(), start.toString(), end.toString());
            } else if (start != null) {
                therapies = therapyService.getByFinishedAndPatientNameAndStartDate(finished, patient.getFirstName(), patient.getLastName(), start.toString());
            } else if (end != null) {
                therapies = therapyService.getByFinishedAndPatientNameAndEndDate(finished, patient.getFirstName(), patient.getLastName(), end.toString());
            } else {
                therapies = therapyService.getByFinishedAndPatientName(finished, patient.getFirstName(), patient.getLastName());
            }
        } else {
            if (start != null && end != null) {
                therapies = therapyService.getByFinishedAndDateRange(finished, start.toString(), end.toString());
            } else if (start != null) {
                therapies = therapyService.getByFinishedAndStartDate(finished, start.toString());
            } else if (end != null) {
                therapies = therapyService.getByFinishedAndEndDate(finished, end.toString());
            } else {
                therapies = therapyService.getByFinished(finished);
            }
        }
        listView.setTherapies(therapies);
    }

    public static void addMockData(TherapyService therapyService) {
        Patient patient = new Patient("Jürgen", "Test", new Address("Langstrasse", "12k", 7777, "Burgdorf"));
        Therapy therapy = new Therapy(new Date(), new TherapyType("Exposition", ""));
        therapy.setTherapist(new Therapist("user", "1234", new AcademicTitle("Dr.", ""), "Ueli", "Kramer", new Address("Burgstrasse", "18", 3600, "Thun")));
        therapy.setPatient(patient);
        therapyService.add(therapy);
    }
}
