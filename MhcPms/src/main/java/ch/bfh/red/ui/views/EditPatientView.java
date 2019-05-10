package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.encoders.LongToStringEncoder;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Exclude;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

@Route(value = "editpatient", layout = MainLayout.class)
@Tag("editpatient-element")
@HtmlImport("frontend://src/views/person/editPatient.html")
public class EditPatientView extends PolymerTemplate<EditPatientView.EditPatientModel> implements View<EditPatientView.EditPatientViewListener>{
    List<EditPatientViewListener> listeners = new ArrayList<>();

    @Id("header")
    private H2 header;

    /** View Model Interface **/
    public interface EditPatientModel extends TemplateModel {
        @Exclude("id")
        @Encode(value = IntegerToStringEncoder.class, path = "address.plz")
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
        header.setText("Edit Patient");
        Patient p = new Patient("cyrill","meyer",new Address());
        getModel().setPatient(p);

    }

    @EventHandler
    private void save(){
        Patient p = getModel().getPatient();
        System.out.println("save!");
        System.out.println("fname: " + p.getFirstName());
        System.out.println("lname: " + p.getLastName());
        Address a = p.getAddress();
        System.out.println("strasse: " + a.getStreet());
        System.out.println("plz: " + a.getPlz());
        System.out.println("stadt: " + a.getCity());
        Notification.show("Patient konnte erfolgreich gespeichert werden!");
    }

    @EventHandler
    private void cancel(){

    }


}
