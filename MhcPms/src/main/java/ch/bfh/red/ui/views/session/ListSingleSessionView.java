package ch.bfh.red.ui.views.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
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

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.encoders.AcademicTitleToStringEncoder;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.SingleSessionPresenter;
import ch.bfh.red.ui.views.EditSingleSessionView;

@Route(value = "singleSession/list", layout = MainLayout.class)
@Tag("single-session-list")
@HtmlImport("frontend://src/views/session/listSingleSession.html")
@Component
@UIScope
public class ListSingleSessionView 
		extends PolymerTemplate<ListSingleSessionView.ListSingleSessionModel> implements BeforeEnterObserver {
	
	@Autowired
    private SingleSessionPresenter presenter;
	
	@Id("header")
    private H2 header;
	
	@Id("startDate.date")
    private DatePicker startDatePicker;

    @Id("endDate.date")
    private DatePicker endDatePicker;
    
    @Id("patient")
    private ComboBox<Patient> patientComboBox;
	
    private Binder<SingleSession> binder = new Binder<>();
    
    @Autowired
    public ListSingleSessionView(SingleSessionPresenter presenter) {
    	this.presenter = presenter;
    	
    	patientComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
    	
    	binder.forField(patientComboBox)
    		.bind(SingleSession::getPatient, SingleSession::setPatient);
    }
    
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        presenter.setView(this);

        // init view elements
        header.setText("Einzelsitzungen");
//        start.setI18n(MainLayout.datePickerI18n);
//        end.setI18n(MainLayout.datePickerI18n);
        
    }
    
    public void setSingleSessions(List<SingleSession> singleSessions) {
    	getModel().setSingleSessions(singleSessions);
    }
    
    public void setPatients(List<Patient> patients) {
    	patientComboBox.setItems(patients);
//    	getModel().setPatients(patients);
    }
    
    
    @EventHandler
    public void edit(@ModelItem SingleSession singleSession) {
        UI.getCurrent().navigate(EditSingleSessionView.class, singleSession.getId());
    }
    
    @EventHandler
    public void delete(@ModelItem SingleSession singleSession) {
    	new ConfirmationDialog<SingleSession>().open(
                "Einzelsitzung wirklich löschen?",
                "Möchten Sie die Einzelsitzung wirklich löschen?", "", "Löschen",
                true, singleSession, this::confirmDelete);
    }
    
    private void confirmDelete(SingleSession singleSession) {
        if (singleSession == null) return;
        presenter.delete(singleSession);
        Notification.show("Die Therapie wurde erfolgreich gelöscht.");
//        presenter.updateList(currentPatientFilter, currentStartFilter, currentEndFilter);
    }
    
    public interface ListSingleSessionListener {
    	
    	void delete(SingleSession singleSession);
    	
    }
    
    /**
     * Filling patients with attributes led to [object Object] in placeholder
     */
	public interface ListSingleSessionModel extends TemplateModel {
        @Include({"id", "startDate", "finished", "patient.firstName", "patient.lastName", "therapist.academicTitle", "therapist.firstName", "therapist.lastName"})
        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = AcademicTitleToStringEncoder.class, path = "therapist.academicTitle")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        void setSingleSessions(List<SingleSession> singleSessions);

//        @Include({"firstName", "lastName"})
//        void setPatients(List<Patient> patients);
    }
	
}
