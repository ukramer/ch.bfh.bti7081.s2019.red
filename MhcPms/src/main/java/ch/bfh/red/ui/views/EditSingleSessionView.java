package ch.bfh.red.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
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
import ch.bfh.red.ui.encoders.DateTimeBean;
import ch.bfh.red.ui.encoders.DateToDateBeanEncoder;

@Route(value = "editSingleSession", layout = MainLayout.class)
@Tag("editsinglesession-element")
@HtmlImport("frontend://src/views/session/editSingleSession.html")
public class EditSingleSessionView
		extends PolymerTemplate<EditSingleSessionView.EditSingleSessionModel>
		implements View<EditSingleSessionView.EditSingleSessionListener> {
	private static final long serialVersionUID = 1L;
	
	private EditSingleSessionListener listener;
	
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
	
	
	private SingleSession singleSession;
	
	private Binder<SingleSession> binder = new Binder<>();
	
	public EditSingleSessionView() {
		header.setText("Bearbeite eine Einzelsitzung");
		
//		binder.forField(patient)
//			.asRequired("Auswahl leer")
//			.bind(SingleSession::getPatient, SingleSession::setPatient);
//		binder.forField(therapist)
//		.asRequired("Auswahl leer")
//		.bind(SingleSession::getTherapist, SingleSession::setTherapist);
//		binder.forField(sessionType)
//		.asRequired("Auswahl leer")
//		.bind(SingleSession::getSessionType, SingleSession::setSessionType);
		
		Collection<Patient> patients = createPatients();
		Collection<Therapist> therapists = createTherapists();
		Patient patient  = patients.iterator().next();
		Therapist therapist = therapists.iterator().next();
		Collection<SessionType> sessionTypes = createSessionTypes();
		SessionType sessionType = sessionTypes.iterator().next();
		singleSession = new SingleSession(patient, therapist, new Date(), new Date(), sessionType);
		
		
		
		this.patientComboBox.setDataProvider(DataProvider.ofCollection(patients));
		this.therapistComboBox.setDataProvider(DataProvider.ofCollection(therapists));
		this.sessionTypeComboBox.setDataProvider(DataProvider.ofCollection(sessionTypes));


		
		setSingleSession(singleSession);
		
	}
	
	public void createSingleSession(SingleSession singleSession) {
		header.setText("Neue Einzelsitzung");
		setSingleSession(singleSession);
	}
	
	public void editSingleSession(SingleSession singleSession) {
		header.setText("Bearbeite Einzelsitzung");
		setSingleSession(singleSession);
	}
	
	
	private void setSingleSession(SingleSession singleSession) {
		binder.setBean(singleSession);
//		getModel().setSingleSession(singleSession);
		
		DateToDateBeanEncoder converter = new DateToDateBeanEncoder();
		
		DateTimeBean dateTimeBean = new DateTimeBean(singleSession.getStartDate());
		System.out.println(dateTimeBean.getDate());
		System.out.println(dateTimeBean.getTime());
		
//		getModel().setStartDate(singleSession.getStartDate());
		getModel().setStartDate(dateTimeBean);
//		getModel().setStartTime(dateTimeBean);
	}
	
	
	
	// TODO remove
	private Collection<Therapist> createTherapists() {
		List<Therapist> persons = new ArrayList<>();
		AcademicTitle academicTitle = new AcademicTitle("Dr", "");
		Address address1 = new Address("Bahnhofstrasse", "2", 1234, "Zürich");
		Address address2 = new Address("Langstrasse", "40", 1235, "Bern");
		persons.add(new Therapist("hansiRu", "1234", academicTitle, "Reto", "Bachmann", address1));
		persons.add(new Therapist("melbu", "1234", academicTitle, "Laura", "Lütolf", address2));
		return persons;
	}
	
	// TODO remove
	private Collection<Patient> createPatients() {
		List<Patient> patients = new ArrayList<>();
		patients.add(new Patient("Hans", "Rudolf",
				new Address("Bahnhofstrasse", "2", 1234, "Zürich")));
		patients.add(new Patient("Melina", "Büchel",
				new Address("Langstrasse", "40", 1235, "Bern")));
		return patients;
	}
	
	// TODO remove
	private Collection<SessionType> createSessionTypes() {
		List<SessionType> sessionTypes = new ArrayList<>();
		sessionTypes.add(new SessionType("Session", ""));
		sessionTypes.add(new SessionType("Discusssion", ""));
		return sessionTypes;
	}
	
	public interface EditSingleSessionListener {
		
	}
	
	
	
	public interface EditSingleSessionModel extends TemplateModel {
//		@Include({ 
////			"startDate"
////			, 
////			"startDate", "endDate",
////				"endDate", "sessionType"
////			,
//			"patient", "therapist" 
//				})
////		@Encode(value = DateToDateBeanEncoder.class, path = "startDate.dateString")
////		@Encode(value = DateToDateBeanEncoder.class, path = "startDate.timeString")
////		@Encode(value = DateToDateBeanEncoder.class, path = "endDate.dateString")
////		@Encode(value = DateToDateBeanEncoder.class, path = "endDate.timeString")
//		@Encode(value = PatientToNameStringConverter.class, path = "patient")
//		@Encode(value = TherapistToNameStringConverter.class, path = "therapist")
//		void setSingleSession(SingleSession singleSession);
		
		
		
		@Encode(value = DateToDateBeanEncoder.class, path = "startDate")
//		@Include({"date"})
		void setStartDate(DateTimeBean date);
		
//		@Encode(value = DateToDateBeanEncoder.class, path = "startDate")
////		@Include({"time"})
//		void setStartTime(DateTimeBean date);
//		
//		void setStartDate()
		
	}
	
	@Override
	public void setListener(EditSingleSessionListener listener) {
		this.listener = listener;
	}
}
