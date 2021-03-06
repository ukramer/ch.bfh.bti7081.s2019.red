package ch.bfh.red.ui.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.ExpositionPresenter;


@Route(value = "exposition", layout = MainLayout.class)
@Tag("exposition-view")
@HtmlImport("frontend://src/views/therapy/exposition-view.html")
@Component
@UIScope
public class ExpositionView extends PolymerTemplate<ExpositionView.ExpositionViewModel> implements View<ExpositionView.ExpositionViewListener>, BeforeEnterObserver {
    private ExpositionViewListener listener;

    @Id("header")
    private H2 header;

    @Id("vaadinVerticalLayout")
    private VerticalLayout layout;

    private Patient currentPatientFilter = new Patient();

    private ExpositionPresenter expositionPresenter;
    private ConfirmationDialog<ExpositionNote> confirmationDialog = new ConfirmationDialog<>();

    public interface ExpositionViewModel extends TemplateModel {
        @Include({"id", "patient.firstName", "patient.lastName", "text", "date", "degreeOfExposure"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = DateToStringEncoder.class, path = "date")
        @Encode(value = IntegerToStringEncoder.class, path = "degreeOfExposure")
        void setExpositions(List<ExpositionNote> expositions);

        List<ExpositionNote> getExpositions();

        @Include({"firstName", "lastName"})
        void setPatients(List<Patient> patients);

        List<Patient> getPatients();
    }

    public interface ExpositionViewListener {

        void delete(ExpositionNote expositionNote);

        void deleteById(int id);

        void load(Integer id);

        void save(ExpositionNote expositionNote);

        void updateList();

        void updateListByFilter(Patient patient);
    }

    @Autowired
    public ExpositionView(ExpositionPresenter expositionPresenter) {
        this.expositionPresenter = expositionPresenter;
        this.layout.setSizeFull();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        this.expositionPresenter.setView(this);
        header.setText("Expositionen");
    }

    @Override
    public void setListener(ExpositionViewListener listener) {
        this.listener = listener;
    }

    public void setExpositions(List<ExpositionNote> expositions) {
        getModel().setExpositions(expositions);
    }

    public void setPatients(List<Patient> patients) {
        getModel().setPatients(patients);
    }

    @EventHandler
    public void patientFilter(@ModelItem Patient patient) {
        if (patient == null) {
            patient = new Patient();
        }
        currentPatientFilter = patient;
        listener.updateListByFilter(currentPatientFilter);
    }

    @EventHandler
    public void edit(@ModelItem ExpositionNote expositionNote) {
        UI.getCurrent().navigate(ExpositionDetailView.class, expositionNote.getId());
    }

    @EventHandler
    public void delete(@ModelItem ExpositionNote expositionNote) {
        confirmationDialog.open(
                "Exposition wirklich löschen?",
                "Möchten Sie die Expositionsnotiz wirklich löschen?", "", "Löschen",
                true, expositionNote, this::confirmDelete);
    }

    public void confirmDelete(ExpositionNote expositionNote) {
        if (expositionNote == null) return;
        listener.delete(expositionNote);
        Notification.show("Die Exposition wurde erfolgreich gelöscht.");
        getModel().getExpositions().remove(expositionNote);
        //listener.updateList();
    }

    @EventHandler
    private void createExposition() {
        UI.getCurrent().navigate(ExpositionDetailView.class);
    }
    
}

