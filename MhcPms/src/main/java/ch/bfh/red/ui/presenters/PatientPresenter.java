package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.*;
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
        editPatientView.setListener(this);
    }

    public PatientPresenter(ListPatientView listPatientView, PatientService patientService) {
        this.listPatientView = listPatientView;
        this.patientService = patientService;
        listPatientView.setListener(this);
    }

    @Override
    public void searchPatients(PatientSearchBean patientSearchBean) {
        listPatientView.setPatientList(patientService.findByPatientSearchBean(patientSearchBean));
    }

    @Override
    public void deletePatient(int id) {
        patientService.delete(id);
    }

    @Override
    public void save(Patient patient) {
        patientService.persist(patient);
    }

    @Override
    public Patient loadPatient(int id) {
        return patientService.getByIdWithAssociations(id);
    }
}
