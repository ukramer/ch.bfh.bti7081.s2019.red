package ch.bfh.red.ui.presenters;

import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.*;
import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;

public class PatientPresenter implements EditPatientView.EditPatientViewListener, ListPatientView.ListPatientViewListener {
    private EditPatientView editPatientView;
    private ListPatientView listPatientView;
    
//    @Autowired
//    private PatientService patientService;

    public PatientPresenter(EditPatientView editPatientView) {
        this.editPatientView = editPatientView;
        editPatientView.setListener(this);
    }

    public PatientPresenter(ListPatientView listPatientView) {
        this.listPatientView = listPatientView;
        listPatientView.setListener(this);
    }

    @Override
    public void searchPatients(PatientSearchBean patientSearchBean) {
//        listPatientView.setPatientList(patientService.findByPatientSearchBean(patientSearchBean));
    }

    @Override
    public void deletePatient(int id) {
//        patientService.delete(id);
    }

    @Override
    public void save(Patient patient) {
//        patientService.update(patient);
    }

    @Override
    public Patient loadPatient(int id) {
//        return patientService.getByIdWithAssociations(id);
    	return null;
    }
}
