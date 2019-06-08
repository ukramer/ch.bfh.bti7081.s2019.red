package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.*;
import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientPresenter implements EditPatientView.EditPatientViewListener, ListPatientView.ListPatientViewListener {
    private EditPatientView editPatientView;
    private ListPatientView listPatientView;
    private PatientService patientService;


    @Autowired
    public PatientPresenter(PatientService patientService) {
        this.patientService = patientService;
    }

    public void setView(ListPatientView listPatientView){
        this.listPatientView = listPatientView;
        listPatientView.setListener(this);
    }

    public void setView(EditPatientView editPatientView){
        this.editPatientView = editPatientView;
        editPatientView.setListener(this);
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
