package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.AcademicTitle;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.presenters.SingleSessionPresenter;
import ch.bfh.red.ui.views.Therapy.ListView;

@Route(value = "editSingleSession", layout = MainLayout.class)
@Tag("editsinglesession-element")
@HtmlImport("frontend://src/views/session/editSingleSession.html")
public class EditSingleSessionView
		extends PolymerTemplate<EditSingleSessionView.EditSingleSessionModel> 
		implements HasUrlParameter<Integer> {
	
	private SingleSessionPresenter presenter;
	
	@Id("header")
	private H2 header;
	
	@Id("therapist")
	private ComboBox<Therapist> therapistComboBox;
	
	@Id("patient")
	private ComboBox<Patient> patientComboBox;
	
	@Id("sessionType")
	private ComboBox<SessionType> sessionTypeComboBox;
	
	@Id("startDate.date")
	private DatePicker startDatePicker;
	
	@Id("startDate.time")
	private TimePicker startTimePicker;
	
	private Binder<SingleSession> binder = new Binder<>();
	
	@Autowired
	public EditSingleSessionView(SingleSessionPresenter presenter) {
		this.presenter = presenter;
		
		Collection<Patient> patients = presenter.getPatients();
		Collection<Therapist> therapists = presenter.getTherapist();
		Collection<SessionType> sessionTypes = presenter.getSessionTypes();
		
		this.patientComboBox.setDataProvider(DataProvider.ofCollection(patients));
		this.therapistComboBox.setDataProvider(DataProvider.ofCollection(therapists));
		this.sessionTypeComboBox.setDataProvider(DataProvider.ofCollection(sessionTypes));
		
		binder.forField(patientComboBox)
				.asRequired("Auswahl leer")
				.bind(SingleSession::getPatient, SingleSession::setPatient);
		binder.forField(therapistComboBox)
				.asRequired("Auswahl leer")
				.bind(SingleSession::getTherapist, SingleSession::setTherapist);
		binder.forField(sessionTypeComboBox)
				.asRequired("Auswahl leer")
				.bind(SingleSession::getSessionType, SingleSession::setSessionType);
		
		createSingleSession();
		
	}
	
	@Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer integer) {
        presenter.setView(this);

        if (integer == null) {
            presenter.prepareNewObject();
        } else {
            try {
                presenter.load(integer);
            } catch (NoSuchElementException e) {
                // means that there is no element available with the id
                UI.getCurrent().navigate(ListView.class);
            }
        }
    }
	
	@EventHandler
    public void save() {
		BinderValidationStatus<SingleSession> validate = binder.validate();
		if (validate.isOk()) {
			SingleSession singleSession = getSingleSession();
			try {
				presenter.save(singleSession);
				Notification.show("Die Therapie wurde erfolgreich hinzugefügt.");
			} catch (Exception e) {
				Notification.show(e.getMessage());
			}
		} else {
			List<String> errorMessages = new ArrayList<>();
            validate.getValidationErrors().forEach(e -> errorMessages.add(e.getErrorMessage()));
            String errorMessage = errorMessages.stream().collect(Collectors.joining("<br>"));
            Notification.show(errorMessage);
		}
	}
	
	public SingleSession getSingleSession() {
		SingleSession singleSession = binder.getBean();
		Date startDate = DateTimeUtils.toDate(startDatePicker.getValue(), startTimePicker.getValue());
		Date endDate = new Date();
		singleSession.setStartDate(startDate);
		singleSession.setEndDate(endDate);
		return singleSession;
		
	}
	
	public void setPatients(Collection<Patient> patients) {
		patientComboBox.setItems(patients);
	}
	
	public void createSingleSession() {
		header.setText("Neue Einzelsitzung");
		therapistComboBox.clear();
		patientComboBox.clear();
		sessionTypeComboBox.clear();
		startDatePicker.setValue(LocalDate.now());
		startTimePicker.setValue(LocalTime.now());
		
		binder.setBean(new SingleSession());
	}
	
	public void editSingleSession(SingleSession singleSession) {
		header.setText("Bearbeite Einzelsitzung");
		setSingleSession(singleSession);
	}
	
	private void setSingleSession(SingleSession singleSession) {
		binder.setBean(singleSession);
		getModel().setPatient(singleSession.getPatient());
		getModel().setTherapist(singleSession.getTherapist());
		
		startDatePicker.setValue(DateTimeUtils.toLocalDate(singleSession.getStartDate()));
		startTimePicker.setValue(DateTimeUtils.toLocalTime(singleSession.getStartDate()));
	}
	
	
	
	public interface EditSingleSessionListener {
		
		Collection<Patient> getPatients();
		
		Collection<Therapist> getTherapist();
		
		Collection<SessionType> getSessionTypes();
		
		void load(Integer therapyId);
		
		void save(SingleSession singleSession) throws Exception;
		
		void prepareNewObject();
		
	}
	
	/**
	 * Model could not fully implemented with attributes and beans.
	 * The problem is DatePicker and TimePicker. It's not possible to set 
	 * the format and the encoder/decooder properly. Tried to solve it 
	 * with DateTimeBean class, but result was formatting problem.
	 * Workaround is to exchange value external with {@code getValue} and {@code setValue}. 
	 */
	public interface EditSingleSessionModel extends TemplateModel {
		@Include({ "id", "patient.firstName", "patient.lastName" })
		void setPatient(Patient patient);
		
		Patient getPatient();
		
		@Include({ "id", "therapist.academicTitle", "therapist.firstName",
				"therapist.lastName" })
		void setTherapist(Therapist therapist);
		
		Therapist getTherapist();
	}
	
}
