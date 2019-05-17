package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
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
    public void onPatientClick(String param) {
        UI.getCurrent().navigate(EditPatientView.class, param);
    }

    @Override
    public void save(Patient patient) {
        System.out.println("save!");
        System.out.println("Vorname: " + patient.getFirstName());
        System.out.println("Nachname: " + patient.getLastName());
        Address a = patient.getAddress();
        System.out.println("Strasse: " + a.getStreet());
        System.out.println("Hausnr: " + a.getStreetNumber());
        System.out.println("Stadt: " + a.getCity());
        System.out.println("PLZ: " + a.getPostalCode());
    }
}
