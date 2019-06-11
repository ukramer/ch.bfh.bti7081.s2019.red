package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
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
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.dto.SingleSessionDTO;
import ch.bfh.red.ui.presenters.SingleSessionPresenter;

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
	
	@Id("endDate.date")
	private DatePicker endDatePicker;
	
	@Id("endDate.time")
	private TimePicker endTimePicker;
	
	@Id("saveButton")
	private Button saveButton;
	
	@Id("cancelButton")
	private Button cancelButton;
	
	@Id("changedButtons")
	private Div changedButtons;
	
	private Binder<SingleSessionDTO> binder = new Binder<>();
	
	private SingleSessionDTO singleSession;
	
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
				.bind(SingleSessionDTO::getPatient, SingleSessionDTO::setPatient);
		binder.forField(therapistComboBox)
				.asRequired("Auswahl leer")
				.bind(SingleSessionDTO::getTherapist, SingleSessionDTO::setTherapist);
		binder.forField(sessionTypeComboBox)
				.asRequired("Auswahl leer")
				.bind(SingleSessionDTO::getSessionType, SingleSessionDTO::setSessionType);
		
		changedButtons.setVisible(false);
		openCreateMode();
		
		patientComboBox.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(), dto -> dto.getPatient());
		});
		therapistComboBox.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(), dto -> dto.getTherapist());
		});
		sessionTypeComboBox.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(), dto -> dto.getSessionType());
		});
		startDatePicker.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(),
					dto -> DateTimeUtils.toLocalDate(dto.getStartDate()));
		});
		startTimePicker.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(),
					dto -> DateTimeUtils.toLocalTime(dto.getStartDate()));
		});
		endDatePicker.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(),
					dto -> DateTimeUtils.toLocalDate(dto.getEndDate()));
		});
		endTimePicker.addValueChangeListener(event -> {
			updateChangedButtons(event.getValue(),
					dto -> DateTimeUtils.toLocalTime(dto.getEndDate()));
		});
		
		saveButton.addClickListener(event -> saveChanges());
		cancelButton.addClickListener(event -> cancelChanges());
		
	}
	
	@Override
	public void setParameter(   BeforeEvent beforeEvent,
								@OptionalParameter Integer singleSessionId) {
		if (singleSessionId == null) {
			openCreateMode();
		} else {
			try {
				SingleSessionDTO dto = presenter.load(singleSessionId);
				openEditMode(dto);
			} catch (NoSuchElementException e) {
				UI.getCurrent().navigate(ListSingleSessionView.class);
				Notification
						.show("Einzelsitzung nicht gefunden. Id = " + singleSessionId);
			}
		}
	}
	
	public void openCreateMode() {
		header.setText("Neue Einzelsitzung");
		
		clear();
		SingleSessionDTO dto = new SingleSessionDTO();
		dto.setStartDate(new Date());
		dto.setEndDate(DateTimeUtils.toDate(LocalDate.now(), LocalTime.now().plusHours(1)));
		
		setSingleSession(dto);
	}
	
	public void clear() {
		therapistComboBox.clear();
		patientComboBox.clear();
		sessionTypeComboBox.clear();
		startDatePicker.clear();
		startTimePicker.clear();
		endDatePicker.clear();
		endTimePicker.clear();
	}
	
	public void openEditMode(SingleSessionDTO singleSession) {
		header.setText("Bearbeite Einzelsitzung");
		setSingleSession(singleSession);
	}
	
	public void saveChanges() {
		BinderValidationStatus<SingleSessionDTO> validate = binder.validate();
		if (validate.isOk()) {
			SingleSessionDTO singleSession = getSingleSession();
			try {
				presenter.save(singleSession);
				Notification.show("Die Einzelsitzung wurde erfolgreich gespeichert.");
			} catch (Exception e) {
				Notification.show(e.getMessage());
			}
		} else {
			List<String> errorMessages = new ArrayList<>();
			validate.getValidationErrors()
					.forEach(e -> errorMessages.add(e.getErrorMessage()));
			String errorMessage = errorMessages.stream()
					.collect(Collectors.joining("\n"));
			Notification.show(errorMessage);
		}
	}
	
	public void cancelChanges() {
		setSingleSession(singleSession.clone());
	}
	
	public SingleSessionDTO getSingleSession() {
		SingleSessionDTO singleSession = binder.getBean().clone();
		Date startDate = DateTimeUtils.toDateOrNull(startDatePicker.getValue(),
				startTimePicker.getValue());
		Date endDate = DateTimeUtils.toDateOrNull(endDatePicker.getValue(),
				endTimePicker.getValue());
		singleSession.setStartDate(startDate);
		singleSession.setEndDate(endDate);
		return singleSession;
	}
	
	public void setPatients(Collection<Patient> patients) {
		patientComboBox.setItems(patients);
	}
	
	private void setSingleSession(SingleSessionDTO singleSession) {
		this.singleSession = singleSession;
		SingleSessionDTO clone = singleSession.clone();
		binder.setBean(clone);
		getModel().setPatient(clone.getPatient());
		getModel().setTherapist(clone.getTherapist());
		
		LocalDate localStartDate = DateTimeUtils
				.toLocalDateOrNull(singleSession.getStartDate());
		LocalTime localStartTime = DateTimeUtils
				.toLocalTimeOrNull(singleSession.getStartDate());
		startDatePicker.setValue(localStartDate);
		startTimePicker.setValue(localStartTime);
		LocalDate localEndDate = DateTimeUtils.toLocalDateOrNull(singleSession.getEndDate());
		LocalTime localEndTime = DateTimeUtils.toLocalTimeOrNull(singleSession.getEndDate());
		endDatePicker.setValue(localEndDate);
		endTimePicker.setValue(localEndTime);
	}
	
	private <T> void updateChangedButtons(  T newValue,
											Function<SingleSessionDTO, T> mapper) {
		T mappedAttribute = null;
		try {
			mappedAttribute = mapper.apply(singleSession);
		} catch (NullPointerException e) {}
		boolean changed = true;
		if (mappedAttribute != null) {
			changed = (singleSession != null
					&& !mappedAttribute.equals(newValue));
		}
		updateChangedButtons(changed);
	}
	
	private void updateChangedButtons(boolean changed) {
		if (!changed && !hasValueChanged())
			changedButtons.setVisible(false);
		else
			changedButtons.setVisible(true);
	}
	
	private boolean hasValueChanged() {
		if (singleSession == null)
			return false;
		SingleSessionDTO dto = getSingleSession();
		return dto.equals(singleSession);
	}
	
	public interface EditSingleSessionListener {
		
		Collection<Patient> getPatients();
		
		Collection<Therapist> getTherapist();
		
		Collection<SessionType> getSessionTypes();
		
		SingleSessionDTO load(Integer therapyId);
		
		SingleSessionDTO save(SingleSessionDTO singleSession) throws Exception;
		
	}
	
	/**
	 * Model could not fully implemented with attributes and beans. The problem is
	 * DatePicker and TimePicker. It's not possible to set the format and the
	 * encoder/decooder properly. Tried to solve it with DateTimeBean class, but
	 * result was formatting problem. Workaround is to exchange value external with
	 * {@code getValue} and {@code setValue}.
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
