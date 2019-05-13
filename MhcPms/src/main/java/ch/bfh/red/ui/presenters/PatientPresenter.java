package ch.bfh.red.ui.presenters;

import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import com.vaadin.flow.component.UI;

public class PatientPresenter implements EditPatientView.EditPatientViewListener, ListPatientView.ListPatientViewListener {
    EditPatientView editPatientView;
    ListPatientView listPatientView;


    public PatientPresenter(EditPatientView editPatientView) {
        this.editPatientView = editPatientView;
        editPatientView.addListener(this);
    }

    public PatientPresenter(ListPatientView listPatientView) {
        this.listPatientView = listPatientView;
        listPatientView.addListener(this);
    }

    @Override
    public void search(PatientSearchBean patientSearchBean) {
        System.out.println("Vorname: " + patientSearchBean.getFirstName());
        System.out.println("Nachname: " + patientSearchBean.getLastName());
        System.out.println("Strasse: " + patientSearchBean.getStreet());
        System.out.println("Hausnr: " + patientSearchBean.getStreetNr());
        System.out.println("PLZ: " + patientSearchBean.getPostalCode());
        System.out.println("Stadt: " + patientSearchBean.getCity());
    }

    @Override
    public void onPatientClick(String param) {
        UI.getCurrent().navigate(EditPatientView.class, param);
    }
}
