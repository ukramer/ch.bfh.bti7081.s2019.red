package ch.bfh.red.ui.views.Therapy;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.*;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.common.AbstractEditorDialog;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.TherapyPresenter;
import ch.bfh.red.ui.views.View;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route(value = "therapy/list", layout = MainLayout.class)
@Tag("therapy-list")
@HtmlImport("frontend://src/views/therapy/list.html")
@Component
@UIScope
public class ListView extends PolymerTemplate<ListView.TherapyModel> implements View<ListView.ListViewListener> {
    private List<ListViewListener> listeners = new ArrayList<>();

    @Id("header")
    private H2 header;

    @Id("start")
    private DatePicker start;

    @Id("end")
    private DatePicker end;

    @Id("deleteDialog")
    private ConfirmDialog deleteDialog;

    private Patient currentPatientFilter;

    private LocalDate currentStartFilter;

    private LocalDate currentEndFilter;

    private Therapy currentTherapy;

    private TherapyPresenter therapyPresenter;

    private EditDialog editDialog = new EditDialog(this::save);

    public interface ListViewListener {
        void delete(Therapy therapy);
        void save(Therapy therapy, AbstractEditorDialog.Operation operation);

        void updateList(boolean finished, Patient patient, LocalDate start, LocalDate end);
    }

    public interface TherapyModel extends TemplateModel {
        @Include({"id", "startDate", "finished", "patient.firstName", "patient.lastName", "therapist.firstName", "therapist.lastName"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        void setTherapies(List<Therapy> therapies);

        @Include({"firstName", "lastName"})
        void setPatients(List<Patient> patients);
    }

    ListView(@Autowired TherapyService therapyService) {
        header.setText("Therapien");

        // @todo: to be removed
        TherapyPresenter.addMockData(therapyService);
        therapyPresenter = new TherapyPresenter(this, therapyService);
    }

    @Override
    public void addListener(ListViewListener listener) {
        listeners.add(listener);
    }

    public void setTherapies(List<Therapy> therapies) {
        getModel().setTherapies(therapies);
    }

    public void setPatients(List<Patient> patients) {
        getModel().setPatients(patients);
    }

    @EventHandler
    public void delete(@ModelItem Therapy therapy) {
        currentTherapy = therapy;
        deleteDialog.setOpened(true);
    }

    @EventHandler
    public void confirmDelete() {
        if (currentTherapy == null) return;
        listeners.forEach(l -> l.delete(currentTherapy));
        listeners.forEach(l -> l.updateList(false, currentPatientFilter, currentStartFilter, currentEndFilter));
    }

    @EventHandler
    public void patientFilter(@ModelItem Patient patient) {
        currentPatientFilter = patient;
        listeners.forEach(l -> l.updateList(false, currentPatientFilter, currentStartFilter, currentEndFilter));
    }

    @EventHandler
    public void dateFilter() {
        currentStartFilter = start.getValue();
        currentEndFilter = end.getValue();
        listeners.forEach(l -> l.updateList(false, currentPatientFilter, currentStartFilter, currentEndFilter));
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
    public void edit(@ModelItem Therapy therapy) {
        if (editDialog.getElement().getParent() == null) {
            getUI().ifPresent(ui -> ui.add(editDialog));
        }
        editDialog.open(therapyPresenter.getService().getById(therapy.getId()), AbstractEditorDialog.Operation.EDIT);
    }

    public void save(Therapy therapy, AbstractEditorDialog.Operation operation) {
        listeners.forEach(l -> l.save(therapy, operation));
        listeners.forEach(l -> l.updateList(false, currentPatientFilter, currentStartFilter, currentEndFilter));
    }
}
