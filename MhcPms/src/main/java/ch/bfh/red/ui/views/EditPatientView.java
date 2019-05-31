package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.*;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.encoders.LocalDateToStringEncoder;
import ch.bfh.red.ui.presenters.PatientPresenter;
import ch.bfh.red.ui.views.Therapy.DetailView;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Route(value = "editpatient", layout = MainLayout.class)
@Tag("editpatient-element")
@HtmlImport("frontend://src/views/person/editPatient.html")
@Component
@UIScope
public class EditPatientView extends PolymerTemplate<EditPatientView.EditPatientModel> implements View<EditPatientView.EditPatientViewListener>, HasUrlParameter<Integer> {
    EditPatientViewListener listener;
    private Binder<Patient> binder = new Binder<>(Patient.class);

    @Id("header")
    private H2 header;

    @Id("firstName")
    private TextField firstName;

    @Id("lastName")
    private TextField lastName;

    @Id("street")
    private TextField street;

    @Id("streetNr")
    private TextField streetNr;

    @Id("postalCode")
    private TextField postalCode;

    @Id("city")
    private TextField city;

    public EditPatientView() {
        new PatientPresenter(this);
        initBinder();
    }

    public interface EditPatientViewListener {
        void save(Patient patient);

        Patient loadPatient(int id);
    }

    public interface EditPatientModel extends TemplateModel {
        List<Therapy> getTherapies();

        @Include({"id", "therapyType.name", "therapyType.description", "startDateAsLocalDate"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = LocalDateToStringEncoder.class, path = "startDateAsLocalDate")
        void setTherapies(List<Therapy> therapies);

        List<SingleSession> getSingleSessions();

        @Include({"id", "sessionType.name", "sessionType.description", "startDateAsLocalDate", "endDateAsLocalDate"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = LocalDateToStringEncoder.class, path = "startDateAsLocalDate")
        @Encode(value = LocalDateToStringEncoder.class, path = "endDateAsLocalDate")
        void setSingleSessions(List<SingleSession> singleSessions);

        List<GroupSession> getGroupSessions();

        @Include({"id", "sessionType.name", "sessionType.description", "startDateAsLocalDate", "endDateAsLocalDate"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = LocalDateToStringEncoder.class, path = "startDateAsLocalDate")
        @Encode(value = LocalDateToStringEncoder.class, path = "endDateAsLocalDate")
        void setGroupSessions(List<GroupSession> groupSessions);

        List<Therapist> getTherapists();

        @Include({"id", "username", "firstName", "lastName"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        void setTherapists(List<Therapist> therapists);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer patientId) {
        if (patientId == null) {
            header.setText("Neuer Patient erfassen");
            binder.setBean(new Patient("", "", new Address()));
            getModel().setTherapies(new ArrayList<>());
            getModel().setSingleSessions(new ArrayList<>());
            getModel().setGroupSessions(new ArrayList<>());
            getModel().setTherapists(new ArrayList<>());
        } else {
            header.setText("Patient bearbeiten");
            Patient patient = listener.loadPatient(patientId);
            getModel().setTherapies((List<Therapy>) patient.getTherapies());
            getModel().setSingleSessions((List<SingleSession>) patient.getSingleSessions());
            getModel().setGroupSessions((List<GroupSession>) patient.getGroupSessions());
            getModel().setTherapists((List<Therapist>) patient.getTherapists());
            binder.setBean(patient);
        }
    }

    @Override
    public void setListener(EditPatientViewListener listener) {
        this.listener = listener;
    }

    @EventHandler
    private void save() {
        if (binder.validate().isOk()) {
            listener.save(binder.getBean());
            Notification.show("Patient konnte erfolgreich gespeichert werden!");
        }
    }

    @EventHandler
    private void cancel() {
        UI.getCurrent().navigate(ListPatientView.class);
    }

    @EventHandler
    public void editTherapy(@EventData("event.model.item.id") int id) {
        UI.getCurrent().navigate(DetailView.class, id);
    }

    @EventHandler
    public void editSingleSession(@EventData("event.model.item.id") int id) {
        System.out.println("edit single session with id: " + id);
    }

    @EventHandler
    public void editGroupSession(@EventData("event.model.item.id") int id) {
        System.out.println("edit group session with id: " + id);
    }

    private void initBinder() {
        // firstName
        binder.forField(firstName).asRequired("Vorname fehlt")
                .bind(Patient::getFirstName, Patient::setFirstName);

        // lastName
        binder.forField(lastName).asRequired("Nachname fehlt")
                .bind(Patient::getLastName, Patient::setLastName);

        // street
        binder.forField(street).asRequired("Strasse fehlt")
                .bind(person -> person.getAddress().getStreet(),
                        (person, street) -> person.getAddress().setStreet(street));

        // streetNr
        binder.forField(streetNr).asRequired("Strassennummer fehlt")
                .bind(person -> person.getAddress().getStreetNumber(),
                        (person, streetNr) -> person.getAddress().setStreetNumber(streetNr));

        // postaleCode
        binder.forField(postalCode).asRequired("Postleitzahl fehlt")
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("PLZ muss eine Zahl sein"))
                .bind(person -> person.getAddress().getPostalCode(),
                        (person, postaleCode) -> person.getAddress().setPostalCode(postaleCode));
        // city
        binder.forField(city).asRequired("Stadt fehlt")
                .bind(person -> person.getAddress().getCity(),
                        (person, city) -> person.getAddress().setCity(city));
    }
}
