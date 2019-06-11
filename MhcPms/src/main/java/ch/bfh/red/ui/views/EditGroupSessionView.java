package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
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
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.ModelItem;
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
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.dto.GroupSessionDTO;
import ch.bfh.red.ui.dto.PatientDTO;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.GroupSessionPresenter;

@Route(value = "editGroupSession", layout = MainLayout.class)
@Tag("editgroupsession-element")
@HtmlImport("frontend://src/views/session/editGroupSession.html")
public class EditGroupSessionView
		extends PolymerTemplate<EditGroupSessionView.EditGroupSessionModel>
		implements HasUrlParameter<Integer> {
	
	private GroupSessionPresenter presenter;
	
	@Id("header")
	private H2 header;
	
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
	
	@Id("backButton")
	private Button backButton;
	
	@Id("changedButtons")
	private Div changedButtons;
	
	@Id("selectablePatients")
	private ComboBox<PatientDTO> selectablePatients;
	
	@Id("addPatientButton")
	private Button addPatientButton;
	
	private Binder<GroupSessionDTO> binder = new Binder<>();
	
	private GroupSessionDTO dto;
	
	private Collection<PatientDTO> patients = new ArrayList<>();
	
	@Autowired
	public EditGroupSessionView(GroupSessionPresenter presenter) {
		this.presenter = presenter;
		
		Collection<SessionType> sessionTypes = presenter.getSessionTypes();
		
		this.sessionTypeComboBox.setDataProvider(DataProvider.ofCollection(sessionTypes));
		this.selectablePatients.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		
		setPatients(presenter.getPatients());
		updateSelectablePatients();
		
		binder.forField(sessionTypeComboBox)
				.asRequired("Auswahl leer")
				.bind(GroupSessionDTO::getSessionType, GroupSessionDTO::setSessionType);
		
		changedButtons.setVisible(false);
		openCreateMode();
		
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
		backButton.addClickListener(event -> changeToListView());
		
		addPatientButton.addClickListener(event -> {
			PatientDTO selectedPatient = selectablePatients.getValue();
			List<PatientDTO> patients = getModel().getPatients();
			patients.add(selectedPatient);
			getModel().setPatients(new ArrayList<>(patients));
			updateSelectablePatients();
		});
		
	}
	
	@Override
	public void setParameter(   BeforeEvent beforeEvent,
								@OptionalParameter Integer modelId) {
		if (modelId == null) {
			openCreateMode();
		} else {
			try {
				GroupSessionDTO dto = presenter.load(modelId);
				openEditMode(dto);
			} catch (NoSuchElementException e) {
				UI.getCurrent().navigate(ListGroupSessionView.class);
				Notification
						.show("Gruppensitzung nicht gefunden. Id = " + modelId);
			}
		}
	}
	
	@EventHandler
	public void removePatient(@ModelItem PatientDTO patient) {
		List<PatientDTO> patients = getModel().getPatients();
		patients.remove(patient);
		getModel().setPatients(new ArrayList<>(patients));
	}
	
	@EventHandler
	public void navigatePatient(@ModelItem PatientDTO patient) {
		UI.getCurrent().navigate(EditPatientView.class, patient.getId());
	}
	
	public void openCreateMode() {
		header.setText("Neue Gruppensitzung");
		
		clear();
		GroupSessionDTO dto = new GroupSessionDTO();
		dto.setStartDate(new Date());
		dto.setEndDate(
				DateTimeUtils.toDate(LocalDate.now(), LocalTime.now().plusHours(1)));
		
		setDTO(dto);
	}
	
	public void clear() {
		sessionTypeComboBox.clear();
		startDatePicker.clear();
		startTimePicker.clear();
		endDatePicker.clear();
		endTimePicker.clear();
	}
	
	public void openEditMode(GroupSessionDTO dto) {
		header.setText("Bearbeite Gruppensitzung");
		setDTO(dto);
	}
	
	public void saveChanges() {
		BinderValidationStatus<GroupSessionDTO> validate = binder.validate();
		if (validate.isOk()) {
			GroupSessionDTO dto = getDTO();
			try {
				presenter.save(dto);
				Notification.show("Die Gruppensitzung wurde erfolgreich gespeichert.");
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
		setDTO(dto.clone());
	}
	
	public void changeToListView() {
		UI.getCurrent().navigate(ListGroupSessionView.class);
	}
	
	public GroupSessionDTO getDTO() {
		GroupSessionDTO dto = binder.getBean().clone();
		Date startDate = DateTimeUtils.toDateOrNull(startDatePicker.getValue(),
				startTimePicker.getValue());
		Date endDate = DateTimeUtils.toDateOrNull(endDatePicker.getValue(),
				endTimePicker.getValue());
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		return dto;
	}
	
	private void setDTO(GroupSessionDTO dto) {
		this.dto = dto;
		GroupSessionDTO clone = dto.clone();
		binder.setBean(clone);
		
		LocalDate localStartDate = DateTimeUtils
				.toLocalDateOrNull(dto.getStartDate());
		LocalTime localStartTime = DateTimeUtils
				.toLocalTimeOrNull(dto.getStartDate());
		startDatePicker.setValue(localStartDate);
		startTimePicker.setValue(localStartTime);
		LocalDate localEndDate = DateTimeUtils.toLocalDateOrNull(dto.getEndDate());
		LocalTime localEndTime = DateTimeUtils.toLocalTimeOrNull(dto.getEndDate());
		endDatePicker.setValue(localEndDate);
		endTimePicker.setValue(localEndTime);
		
		getModel().setPatients(dto.getPatients());
	}
	
	public void setPatients(Collection<PatientDTO> patients) {
		this.patients = patients;
		updateSelectablePatients();
	}
	
	public void updateSelectablePatients() {
		Collection<PatientDTO> selectedPatients = getModel().getPatients();
		Collection<PatientDTO> patients = this.patients;
		Set<PatientDTO> selectablePatients = new HashSet<>();
		for (PatientDTO dto: patients)
			selectablePatients.add(dto);
		for (PatientDTO dto: selectedPatients)
			selectablePatients.remove(dto);
		this.selectablePatients.setItems(selectablePatients);
	}
	
	private <T> void updateChangedButtons(  T newValue,
											Function<GroupSessionDTO, T> mapper) {
		T mappedAttribute = null;
		try {
			mappedAttribute = mapper.apply(this.dto);
		} catch (NullPointerException e) {}
		boolean changed = true;
		if (mappedAttribute != null) {
			changed = (this.dto != null
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
		if (this.dto == null)
			return false;
		GroupSessionDTO dto = getDTO();
		return dto.equals(this.dto);
	}
	
	public interface EditGroupSessionListener {
		
		Collection<PatientDTO> getPatients();
		
		Collection<Therapist> getTherapist();
		
		Collection<SessionType> getSessionTypes();
		
		GroupSessionDTO load(Integer therapyId);
		
		GroupSessionDTO save(GroupSessionDTO singleSession) throws Exception;
		
	}
	
	/**
	 * Model could not fully implemented with attributes and beans. The problem is
	 * DatePicker and TimePicker. It's not possible to set the format and the
	 * encoder/decooder properly. Tried to solve it with DateTimeBean class, but
	 * result was formatting problem. Workaround is to exchange value external with
	 * {@code getValue} and {@code setValue}.
	 */
	public interface EditGroupSessionModel extends TemplateModel {
		
		@Include({"id", "firstName", "lastName"})
		@Encode(value = IntegerToStringEncoder.class, path = "id")
		void setPatients(List<PatientDTO> patients);
		
		List<PatientDTO> getPatients();
		
		@Include({ "id", "patient.firstName", "patient.lastName" })
		void setPatient(Patient patient);
		
		Patient getPatient();
		
		@Include({ "id", "therapist.academicTitle", "therapist.firstName",
				"therapist.lastName" })
		void setTherapist(Therapist therapist);
		
		Therapist getTherapist();
		
	}
	
}
