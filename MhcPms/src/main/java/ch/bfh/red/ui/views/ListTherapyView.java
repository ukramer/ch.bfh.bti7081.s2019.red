package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.encoders.AcademicTitleToStringEncoder;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.TherapyPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Route(value = "therapy/list", layout = MainLayout.class)
@Tag("therapy-list")
@HtmlImport("frontend://src/views/therapy/list.html")
@Component
@UIScope
public class ListTherapyView extends PolymerTemplate<ListTherapyView.TherapyModel> implements View<ListTherapyView.ListViewListener>, BeforeEnterObserver {
    private ListViewListener listener;

    @Id("header")
    private H2 header;

    @Id("start")
    private DatePicker start;

    @Id("end")
    private DatePicker end;

    @Id("showFinished")
    private Checkbox showFinished;

    private ConfirmationDialog<Therapy> confirmationDialog = new ConfirmationDialog<>();

    private Patient currentPatientFilter = new Patient();

    private LocalDate currentStartFilter;

    private LocalDate currentEndFilter;

    private boolean currentShowFinished;

    private TherapyPresenter therapyPresenter;

    @Autowired
    ListTherapyView(TherapyPresenter therapyPresenter) {
        this.therapyPresenter = therapyPresenter;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        therapyPresenter.setView(this);

        // init view elements
        header.setText("Therapien");
        start.setI18n(MainLayout.datePickerI18n);
        end.setI18n(MainLayout.datePickerI18n);
    }

    @Override
    public void setListener(ListViewListener listener) {
        this.listener = listener;
    }

    public void setTherapies(List<Therapy> therapies) {
        getModel().setTherapies(therapies);
    }

    public void setPatients(List<Patient> patients) {
        getModel().setPatients(patients);
    }

    @EventHandler
    public void delete(@ModelItem Therapy therapy) {
        confirmationDialog.open(
                "Therapie wirklich löschen?",
                "Möchten Sie die Therapie wirklich löschen?", "", "Löschen",
                true, therapy, this::confirmDelete);
    }

    public void confirmDelete(Therapy therapy) {
        if (therapy == null) return;
        listener.delete(therapy);
        Notification.show("Die Therapie wurde erfolgreich gelöscht.");
        listener.updateList(currentShowFinished, currentPatientFilter, currentStartFilter, currentEndFilter);
    }

    @EventHandler
    public void patientFilter(@ModelItem Patient patient) {
        if (patient == null) {
            patient = new Patient();
        }
        currentPatientFilter = patient;
        listener.updateList(currentShowFinished, currentPatientFilter, currentStartFilter, currentEndFilter);
    }

    @EventHandler
    public void dateFilter() {
        currentStartFilter = start.getValue();
        currentEndFilter = end.getValue();
        listener.updateList(currentShowFinished, currentPatientFilter, currentStartFilter, currentEndFilter);
    }

    @EventHandler
    public void startChanged() {
        dateFilter();
    }

    @EventHandler
    public void endChanged() {
        dateFilter();
    }

    @EventHandler
    public void showFinished() {
        currentShowFinished = showFinished.getValue();
        listener.updateList(currentShowFinished, currentPatientFilter, currentStartFilter, currentEndFilter);
    }

    @EventHandler
    public void edit(@ModelItem Therapy therapy) {
        UI.getCurrent().navigate(EditTherapyView.class, therapy.getId());
    }

    @EventHandler
    public void add() {
        UI.getCurrent().navigate(EditTherapyView.class);
    }

    public interface ListViewListener {
        void delete(Therapy therapy);

        void updateList(boolean finished, Patient patient, LocalDate start, LocalDate end);
    }

    public interface TherapyModel extends TemplateModel {
        @Include({"id", "startDate", "finished", "patient.firstName", "patient.lastName", "therapist.academicTitle", "therapist.firstName", "therapist.lastName"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = AcademicTitleToStringEncoder.class, path = "therapist.academicTitle")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        void setTherapies(List<Therapy> therapies);

        @Include({"firstName", "lastName"})
        void setPatients(List<Patient> patients);
    }
}
