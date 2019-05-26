package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.services.PatientService;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.PatientPresenter;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Route(value = "listpatient", layout = MainLayout.class)
@Tag("listpatient-element")
@HtmlImport("frontend://src/views/person/listPatient.html")
@Component
@UIScope
public class ListPatientView extends PolymerTemplate<ListPatientView.ListPatientModel> implements View<ListPatientView.ListPatientViewListener> {
    private ListPatientViewListener listener;
    private PatientPresenter patientPresenter;
    private Binder<PatientSearchBean> binder = new Binder<>(PatientSearchBean.class);

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

    @Override
    public void addListener(ListPatientViewListener listener) {
        this.listener = listener;
    }

    ListPatientView(@Autowired PatientService patientService) {
        resetView();
        this.patientPresenter = new PatientPresenter(this, patientService);
        patientPresenter.addMockData();
        header.setText("Patienten");
        binder.setBean(new PatientSearchBean());
        initBinder();
    }

    private void resetView() {
        getModel().setPatients(new ArrayList<>());
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
        listener.deletePatient(id);
    }

    @EventHandler
    public void search() {
        listener.searchPatients(binder.getBean());
    }

    @EventHandler
    public void addPatient() {
        UI.getCurrent().navigate(EditPatientView.class);
    }
}
