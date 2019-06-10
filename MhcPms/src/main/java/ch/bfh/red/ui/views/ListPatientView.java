package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.*;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.PatientPresenter;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
@Tag("listpatient-element")
@HtmlImport("frontend://src/views/person/listPatient.html")
@Component
@UIScope
public class ListPatientView extends PolymerTemplate<ListPatientView.ListPatientModel> implements View<ListPatientView.ListPatientViewListener>, BeforeEnterObserver {
    private ListPatientViewListener listener;
    private Binder<PatientSearchBean> binder = new Binder<>(PatientSearchBean.class);
    private ConfirmationDialog<Integer> confirmationDialog = new ConfirmationDialog<>();

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

    @Id("header")
    private H2 header;

    ListPatientView(@Autowired PatientService patientService) {
        new PatientPresenter(this, patientService);
        header.setText("Patienten");
        initBinder();
    }

    public interface ListPatientViewListener {
        void searchPatients(PatientSearchBean patientSearchBean);

        void deletePatient(int id);
    }

    public interface ListPatientModel extends TemplateModel {
        @Include({"id", "firstName", "lastName", "address.street", "address.streetNumber", "address.postalCode", "address.city"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        void setPatients(List<Patient> patienten);

        List<Patient> getPatients();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        resetView();
    }

    @Override
    public void setListener(ListPatientViewListener listener) {
        this.listener = listener;
    }

    public void setPatientList(List<Patient> patientList) {
        getModel().setPatients(patientList);
    }

    @EventHandler
    public void edit(@ModelItem Patient patient) {
        UI.getCurrent().navigate(EditPatientView.class, patient.getId());
    }

    @EventHandler
    public void delete(@EventData("event.model.item.id") int id) {
        confirmationDialog.open(
                "Patient wirklich löschen?",
                "Möchten Sie den Patient wirklich löschen?", "", "Löschen",
                true, id, this::confirmDelete);
    }

    public void confirmDelete(int id) {
        listener.deletePatient(id);
        Notification.show("Patient konnte erfolgreich gelöscht werden!");
        listener.searchPatients(binder.getBean());
    }

    @EventHandler
    public void search() {
        listener.searchPatients(binder.getBean());
    }

    @EventHandler
    public void addPatient() {
        UI.getCurrent().navigate(EditPatientView.class);
    }

    private void initBinder() {
        // firstName
        binder.forField(firstName).bind(PatientSearchBean::getFirstName, PatientSearchBean::setFirstName);

        // lastName
        binder.forField(lastName).bind(PatientSearchBean::getLastName, PatientSearchBean::setLastName);

        // street
        binder.forField(street).bind(person -> person.getStreet(),
                (person, street) -> person.setStreet(street));

        // streetNr
        binder.forField(streetNr).bind(person -> person.getStreetNr(),
                (person, streetNr) -> person.setStreetNr(streetNr));

        // postaleCode
        binder.forField(postalCode)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("PLZ muss eine Zahl sein"))
                .bind(person -> person.getPostalCode(),
                        (person, postaleCode) -> person.setPostalCode(postaleCode));
        // city
        binder.forField(city).bind(person -> person.getCity(),
                (person, city) -> person.setCity(city));

    }

    private void resetView() {
        setPatientList(new ArrayList<>());
        binder.setBean(new PatientSearchBean());
    }
}
