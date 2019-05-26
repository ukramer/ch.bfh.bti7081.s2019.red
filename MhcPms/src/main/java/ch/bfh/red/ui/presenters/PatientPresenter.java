package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.backend.services.SingleSessionService;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;

import java.util.Date;

public class PatientPresenter implements EditPatientView.EditPatientViewListener, ListPatientView.ListPatientViewListener {
    private EditPatientView editPatientView;
    private ListPatientView listPatientView;
    private PatientService patientService;


    public PatientPresenter(EditPatientView editPatientView, PatientService patientService) {
        this.editPatientView = editPatientView;
        this.patientService = patientService;
        editPatientView.setListener(this);
    }

    public PatientPresenter(ListPatientView listPatientView, PatientService patientService) {
        this.listPatientView = listPatientView;
        this.patientService = patientService;
        listPatientView.setListener(this);
    }

    @Override
    public void searchPatients(PatientSearchBean patientSearchBean) {
        System.out.println(patientSearchBean.toString());
        listPatientView.setPatientList(patientService.findByPatientSearchBean(patientSearchBean));
    }

    @Override
    public void deletePatient(int id) {
        patientService.delete(id);
        System.out.println("delete patient with index: " + id);
    }

    @Override
    public void save(Patient patient) {
        patientService.update(patient);
    }

    @Override
    public Patient loadPatient(int id) {
        Patient p = patientService.getByIdWithAssociations(id);
        return p;
    }

    public void addMockData(TherapyService therapyService, SingleSessionService singleSessionService, TherapistService therapistService) {
        Patient p1 = patientService.update(new Patient("cyrill", "meyer", new Address("bethlehem", "7", 3185, "schmitten")));
        Patient p2 = patientService.update(new Patient("ueli", "kramer", new Address("thunstrasse", "18", 2499, "thun")));
        Therapy t1 = new Therapy(new Date(21,12,2018), new TherapyType("Exposition", "Beschreibung zur Therapyart"));
        Therapy t2 = new Therapy(new Date(20,04,2019), new TherapyType("Therapy XY", "Beschreibung zur Therapy XY"));
        Therapist therapist = therapistService.update(new Therapist("user", "1234", new AcademicTitle("Dr.", ""), "Susi", "Knüller", new Address("Burgstrasse", "18", 3600, "Thun")));
        SingleSession s1 = new SingleSession(
                p1,
                therapist,
                new Date(21,03,2019),
                new Date(22,05,2019),
                new SessionType("Single Session Type", "beschreibung"));
        singleSessionService.add(s1);
        t1.setPatient(p1);
        t2.setPatient(p1);
        therapyService.add(t1);
        therapyService.add(t2);
    }
}
