package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;

@Route(value = "editsinglesession", layout = MainLayout.class)
@Tag("editsinglesession-element")
public class EditSingleSessionView extends FormLayout {
	
	public EditSingleSessionView() {
		TextField therapistTextField = new TextField();
		therapistTextField.setValueChangeMode(ValueChangeMode.EAGER);
		therapistTextField.setEnabled(false);
		therapistTextField.setValue("Dr. Hutzli");
		therapistTextField.setClassName("full-width");
		addFormItem(therapistTextField, "Therapist");
		
		ComboBox<Patient> patientComboBox = new ComboBox<>();
		Collection<Patient> patients = createPatients();
		patientComboBox.setItems(patients);
		patientComboBox.setItemLabelGenerator(createItemLabelGenerator(
				patient -> patient.getLastName() + " " + patient.getFirstName()));
		addFormItem(patientComboBox, "Patient");
		
		ComboBox<SessionType> sessionTypeComboBox = new ComboBox<>();
		Collection<SessionType> sessionTypes = createSessionTypes();
		sessionTypeComboBox.setItems(sessionTypes);
		sessionTypeComboBox
				.setItemLabelGenerator(createItemLabelGenerator(type -> type.getCode()));
		addFormItem(sessionTypeComboBox, "Session type");
		
		DatePicker startDatePicker = new DatePicker();
		startDatePicker.setValue(LocalDate.now());
		addFormItem(startDatePicker, "Start date");
		
		TimePicker startTimePicker = new TimePicker();
		startTimePicker.setValue(LocalTime.now());
		addFormItem(startTimePicker, "Start time");
		
		DatePicker endDatePicker = new DatePicker();
		endDatePicker.setValue(LocalDate.now());
		addFormItem(endDatePicker, "End date");
		
		TimePicker endTimePicker = new TimePicker();
		endTimePicker.setValue(LocalTime.now().plusHours(1l));
		addFormItem(endTimePicker, "End time");	
		
	}
	
	private Collection<Patient> createPatients() {
		List<Patient> patients = new ArrayList<>();
		patients.add(new Patient("Hans", "Rudolf",
				new Address("Bahnhofstrasse", "2", 1234, "Zürich")));
		patients.add(new Patient("Melina", "Büchel",
				new Address("Langstrasse", "40", 1235, "Bern")));
		return patients;
	}
	
	private Collection<SessionType> createSessionTypes() {
		List<SessionType> sessionTypes = new ArrayList<>();
		sessionTypes.add(SessionType.DISCUSSION);
		sessionTypes.add(SessionType.TALK);
		return sessionTypes;
	}
	
	public static <S, T> T convertIfNotNull(S source, Function<S, T> converter) {
		return convertIfNotNull(source, converter, () -> null);
	}

	public static <S, T> T convertIfNotNull(S source, Function<S, T> converter, Supplier<T> nullValueSupplier) {
		return source != null ? converter.apply(source) : nullValueSupplier.get();
	}

	public static <T> ItemLabelGenerator<T> createItemLabelGenerator(Function<T, String> converter) {
		return item -> convertIfNotNull(item, converter, () -> "");
	}
	
}
