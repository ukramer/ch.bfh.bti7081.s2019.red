package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.PatientPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

@Route(value = "editpatient", layout = MainLayout.class)
@Tag("editpatient-element")
@HtmlImport("frontend://src/views/person/editPatient.html")
public class EditPatientView extends PolymerTemplate<EditPatientView.EditPatientModel> implements View<EditPatientView.EditPatientViewListener>, HasUrlParameter<String> {
    List<EditPatientViewListener> listeners = new ArrayList<>();
    @Id("header")
    private H2 header;

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        if(s==null ||s.isEmpty()){
            getModel().setPatient(new Patient("","", new Address()));
        }else{
            System.out.println("find patient with id: " + s + " on db and set on model");
            Patient p = new Patient("cyrill", "meyer", new Address("bethlehem", "7", 3185, "schmitten")); //can be replaced with found patient
            getModel().setPatient(p);
        }
    }

    /** View Model Interface **/
    public interface EditPatientModel extends TemplateModel {
        @Include({"firstName", "lastName", "address.street", "address.streetNumber", "address.postalCode", "address.city"})
        @Encode(value = IntegerToStringEncoder.class, path = "address.postalCode")
        void setPatient(Patient patient);
        Patient getPatient();
    }
    public interface EditPatientViewListener{


    }
    @Override
    public void addListener(EditPatientViewListener listener) {
        listeners.add(listener);
    }

    public EditPatientView(){
        new PatientPresenter(this);
        header.setText("Edit Patient");
    }

    @EventHandler
    private void save(){
        Patient p = getModel().getPatient();
        System.out.println("save!");
        System.out.println("Vorname: " + p.getFirstName());
        System.out.println("Nachname: " + p.getLastName());
        Address a = p.getAddress();
        System.out.println("Strasse: " + a.getStreet());
        System.out.println("Hausnr: " + a.getStreetNumber());
        System.out.println("Stadt: " + a.getCity());
        System.out.println("PLZ: " + a.getPostalCode());
        Notification.show("Patient konnte erfolgreich gespeichert werden!");
    }

    @EventHandler
    private void cancel(){

    }


}
