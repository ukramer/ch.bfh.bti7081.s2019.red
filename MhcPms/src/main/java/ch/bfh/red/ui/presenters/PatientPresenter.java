package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;

public class PatientPresenter implements EditPatientView.EditPatientViewListener, ListPatientView.ListPatientViewListener {
    private EditPatientView editPatientView;
    private ListPatientView listPatientView;
    private PatientService patientService;


    public PatientPresenter(EditPatientView editPatientView, PatientService patientService) {
        this.editPatientView = editPatientView;
        this.patientService = patientService;
        editPatientView.addListener(this);
    }

    public PatientPresenter(ListPatientView listPatientView, PatientService patientService) {
        this.listPatientView = listPatientView;
        this.patientService = patientService;
        listPatientView.addListener(this);
    }

    @Override
    public void searchPatients(PatientSearchBean patientSearchBean) {
        System.out.println(patientSearchBean.toString());
        listPatientView.setPatientList(patientService.findByPatientSearchBean(patientSearchBean));
    }

    @Override
    public void deletePatient(int id) {
        System.out.println("delete patient with index: " + id);
    }

    @Override
    public void save(Patient patient) {
        patientService.update(patient);
    }

    @Override
    public Patient loadPatient(int id) {
        return patientService.getById(id);
    }

    public void addMockData() {
        Patient p1 = new Patient("cyrill", "meyer", new Address("bethlehem", "7", 3185, "schmitten"));
        Patient p2 = new Patient("ueli", "kramer", new Address("thunstrasse", "18", 2499, "thun"));
        patientService.update(p1);
        patientService.update(p2);
    }
}
