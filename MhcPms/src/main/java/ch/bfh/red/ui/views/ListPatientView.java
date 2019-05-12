package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.encoders.LongToStringEncoder;
import ch.bfh.red.ui.presenters.PatientPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Exclude;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

@Route(value = "listpatient", layout = MainLayout.class)
@Tag("listpatient-element")
@HtmlImport("frontend://src/views/person/listPatient.html")
public class ListPatientView extends PolymerTemplate<ListPatientView.ListPatientModel> implements View<ListPatientView.ListPatientViewListener>{
    List<ListPatientViewListener> listeners = new ArrayList<>();
    private List<Patient> patientList = new ArrayList<>();

    @Id("header")
    private H2 header;

    public interface ListPatientViewListener{

    }

    public interface ListPatientModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        void setPatienten(List<Patient> patienten);
        List<Patient> getPatienten();
        @Exclude("id")
        @Encode(value = IntegerToStringEncoder.class, path = "address.plz")
        void setPatient(Patient patient); //TODO replace with SearchPatient Bean
        Patient getPatient();
    }

    @Override
    public void addListener(ListPatientViewListener listener) {
        listeners.add(listener);
    }

    ListPatientView(){
        new PatientPresenter(this);
        header.setText("List Patient");
        patientList.add(new Patient(1,"cyrill", "meyer", new Address("bethlehem 7", 3185, "schmitten")));
        patientList.add(new Patient(2, "ueli", "kramer", new Address("thunstrasse 3", 2400, "thun")));
        getModel().setPatienten(patientList);
        getModel().setPatient(new Patient("", "", new Address()));
    }

    @EventHandler
    public void onClick(@RepeatIndex int itemIndex) {
        String param = patientList.get(itemIndex).getId()+"";
        UI.getCurrent().navigate(EditPatientView.class, param);
    }

    @EventHandler
    private void search(){
        Patient searchPatient = getModel().getPatient();
        System.out.println("Vorname: " + searchPatient.getFirstName());
        System.out.println("Nachname: " + searchPatient.getLastName());
        System.out.println("Strasse: " + searchPatient.getAddress().getStreet());
        System.out.println("PLZ: " + searchPatient.getAddress().getPlz());
        System.out.println("Stadt: " + searchPatient.getAddress().getCity());
    }


}
