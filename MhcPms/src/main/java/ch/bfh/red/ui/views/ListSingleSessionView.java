package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.dto.SingleSessionDTO;
import ch.bfh.red.ui.dto.SingleSessionSearchDTO;
import ch.bfh.red.ui.encoders.AcademicTitleToStringEncoder;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.SingleSessionPresenter;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Route(value = "singleSession/list", layout = MainLayout.class)
@Tag("single-session-list")
@HtmlImport("frontend://src/views/session/listSingleSession.html")
@Component
@UIScope
public class ListSingleSessionView
        extends PolymerTemplate<ListSingleSessionView.ListSingleSessionModel>
        implements BeforeEnterObserver {

    private SingleSessionPresenter presenter;

    @Id("header")
    private H2 header;

    @Id("startDate.date")
    private DatePicker startDatePicker;

    @Id("endDate.date")
    private DatePicker endDatePicker;

    @Id("patient")
    private ComboBox<Patient> patientComboBox;

    @Id("newButton")
    private Button newButton;

    private Binder<SingleSessionDTO> binder = new Binder<>();

    private SingleSessionSearchDTO searchBean = new SingleSessionSearchDTO();

    @Autowired
    public ListSingleSessionView(SingleSessionPresenter presenter) {
        this.presenter = presenter;

        patientComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));

        binder.forField(patientComboBox)
                .bind(SingleSessionDTO::getPatient, SingleSessionDTO::setPatient);


        patientComboBox.addValueChangeListener(event -> {
            PatientSearchBean patientSearchBean = new PatientSearchBean();
            Patient patient = event.getValue();
            if (patient != null) {
                patientSearchBean.setFirstName(patient.getFirstName());
                patientSearchBean.setLastName(patient.getLastName());
            }
            this.searchBean.setPatient(patientSearchBean);
            applyFilter();
        });

        startDatePicker.addValueChangeListener(event -> {
            LocalDate localDate = event.getValue();
            Date date = null;
            if (localDate != null)
                date = DateTimeUtils.toDate(localDate);
            searchBean.setStartDate(date);
            applyFilter();
        });

        endDatePicker.addValueChangeListener(event -> {
            LocalDate localDate = event.getValue();
            Date date = null;
            if (localDate != null)
                date = DateTimeUtils.toDate(localDate);
            searchBean.setEndDate(date);
            applyFilter();
        });

        newButton.addClickListener(event -> changeToCreateMode());

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        presenter.setView(this);

        // init view elements
        header.setText("Einzelsitzungen");
        startDatePicker.setI18n(MainLayout.datePickerI18n);
        endDatePicker.setI18n(MainLayout.datePickerI18n);
    }

    public void changeToCreateMode() {
        UI.getCurrent().navigate(EditSingleSessionView.class);
    }

    public void setSingleSessions(List<SingleSessionDTO> singleSessions) {
        getModel().setSingleSessions(singleSessions);
    }

    public void setPatients(List<Patient> patients) {
        patientComboBox.setItems(patients);
    }

    public void applyFilter() {
        applyFilter(this.searchBean);
    }

    public void applyFilter(SingleSessionSearchDTO searchBean) {
        presenter.applyFilter(searchBean);
    }

    @EventHandler
    public void edit(@ModelItem SingleSessionDTO singleSession) {
        UI.getCurrent().navigate(EditSingleSessionView.class, singleSession.getId());
    }

    @EventHandler
    public void delete(@ModelItem SingleSessionDTO singleSession) {
        new ConfirmationDialog<SingleSessionDTO>().open(
                "Einzelsitzung wirklich löschen?",
                "Möchten Sie die Einzelsitzung wirklich löschen?", "", "Löschen",
                true, singleSession, this::confirmDelete);
    }

    private void confirmDelete(SingleSessionDTO singleSession) {
        if (singleSession == null)
            return;
        presenter.delete(singleSession);
        Notification.show("Die Therapie wurde erfolgreich gelöscht.");
        getModel().getSingleSessions().remove(singleSession);
    }

    public interface ListSingleSessionListener {

        void delete(SingleSessionDTO singleSession);

        void applyFilter(SingleSessionSearchDTO searchBean);

    }

    /**
     * Filling patients with attributes led to [object Object] in placeholder
     */
    public interface ListSingleSessionModel extends TemplateModel {
        List<SingleSessionDTO> getSingleSessions();

        @Include({"id", "startDate", "finished", "patient.firstName",
                "patient.lastName", "therapist.academicTitle", "therapist.firstName",
                "therapist.lastName"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = AcademicTitleToStringEncoder.class,
                path = "therapist.academicTitle")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        void setSingleSessions(List<SingleSessionDTO> singleSessions);
    }

}
