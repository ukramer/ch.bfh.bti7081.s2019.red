package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.dto.GroupSessionDTO;
import ch.bfh.red.ui.dto.GroupSessionGridDTO;
import ch.bfh.red.ui.dto.GroupSessionSearchDTO;
import ch.bfh.red.ui.dto.PatientDTO;
import ch.bfh.red.ui.dto.TherapistDTO;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.GroupSessionPresenter;

@Route(value = "groupSession/list", layout = MainLayout.class)
@Tag("group-session-list")
@HtmlImport("frontend://src/views/session/listGroupSession.html")
@Component
@UIScope
public class ListGroupSessionView
		extends PolymerTemplate<ListGroupSessionView.ListGroupSessionModel>
		implements BeforeEnterObserver {
	
	private GroupSessionPresenter presenter;
	
	@Id("header")
	private H2 header;
	
	@Id("patient")
	private ComboBox<PatientDTO> patientComboBox;
	
	@Id("therapist")
	private ComboBox<TherapistDTO> therapistComboBox;
	
	@Id("startDate.date")
	private DatePicker startDatePicker;
	
	@Id("endDate.date")
	private DatePicker endDatePicker;
	
	private Binder<GroupSessionSearchDTO> binder = new Binder<>();
	
	private GroupSessionSearchDTO searchBean = new GroupSessionSearchDTO();
	
	private boolean detectChanges = false;
	
	@Autowired
	public ListGroupSessionView(GroupSessionPresenter presenter) {
		this.presenter = presenter;
		
		patientComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		therapistComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		
		binder.forField(patientComboBox)
				.bind(GroupSessionSearchDTO::getPatient,
						GroupSessionSearchDTO::setPatient);
		
		patientComboBox.addValueChangeListener(event -> {
			if (detectChanges) {
				PatientDTO patientSearchBean = event.getValue();
				this.searchBean.setPatient(patientSearchBean);
				applyFilter();
			}
		});
		
		therapistComboBox.addValueChangeListener(event -> {
			if (detectChanges) {
				TherapistDTO patientSearchBean = event.getValue();
				PatientDTO personDTO = new PatientDTO();
				if (patientSearchBean != null) {
					personDTO.setFirstName(patientSearchBean.getFirstName());
					personDTO.setLastName(patientSearchBean.getLastName());
				}
				this.searchBean.setTherapist(personDTO);
				applyFilter();
			}
		});
		
		startDatePicker.addValueChangeListener(event -> {
			if (detectChanges) {
				LocalDate localDate = event.getValue();
				Date date = null;
				if (localDate != null)
					date = DateTimeUtils.toDate(localDate);
				searchBean.setStartDate(date);
				applyFilter();
			}
		});
		
		endDatePicker.addValueChangeListener(event -> {
			if (detectChanges) {
				LocalDate localDate = event.getValue();
				Date date = null;
				if (localDate != null)
					date = DateTimeUtils.toDate(localDate);
				searchBean.setEndDate(date);
				applyFilter();
			}
		});
		
		this.detectChanges = true;
		
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		presenter.setView(this);
		
		// init view elements
		header.setText("Gruppensitzungen");
		startDatePicker.setI18n(MainLayout.datePickerI18n);
		endDatePicker.setI18n(MainLayout.datePickerI18n);
	}
	
	@EventHandler
    public void add() {
        UI.getCurrent().navigate(EditGroupSessionView.class);
    }
	
	@EventHandler
	public void edit(@ModelItem GroupSessionGridDTO dto) {
		UI.getCurrent().navigate(EditGroupSessionView.class, dto.getId());
	}
	
	@EventHandler
	public void delete(@ModelItem GroupSessionGridDTO dto) {
		new ConfirmationDialog<GroupSessionGridDTO>().open(
				"Gruppensitzung wirklich löschen?",
				"Möchten Sie die Gruppensitzung wirklich löschen?", "", "Löschen",
				true, dto, this::confirmDelete);
	}
	
	private void confirmDelete(GroupSessionGridDTO dto) {
		if (dto == null)
			return;
		presenter.delete(dto);
		Notification.show("Die Gruppensitzung wurde erfolgreich gelöscht.");
		getModel().getGroupSessions().remove(dto);
	}
	
	public void setGroupSessions(List<GroupSessionDTO> dtos) {
		HashSet<PatientDTO> patientsDTO = new HashSet<>();
		for (GroupSessionDTO dto : dtos)
			patientsDTO.addAll(dto.getPatients());
		patientComboBox.setItems(patientsDTO);
		
		HashSet<TherapistDTO> therapistsDTO = new HashSet<>();
		for (GroupSessionDTO dto : dtos)
			therapistsDTO.addAll(dto.getTherapists());
		therapistComboBox.setItems(therapistsDTO);

		setFilteredSessions(dtos);
	}
	
	public void setFilteredSessions(List<GroupSessionDTO> filterDTOs) {
		List<GroupSessionGridDTO> gridList = new ArrayList<>();
		DateToStringEncoder encoder = new DateToStringEncoder();
		for (GroupSessionDTO groupSession : filterDTOs) {
			Integer id = groupSession.getId();
			String startDate = encoder.encode(groupSession.getStartDate());
			String patients = groupSession.getPatients().stream()
					.map(p -> p.getLastName()).collect(Collectors.joining(", "));
			String therapists = groupSession.getTherapists().stream()
					.map(t -> t.getLastName())
					.collect(Collectors.joining(", "));
			gridList.add(new GroupSessionGridDTO(id, startDate, patients, therapists));
		}
		
		getModel().setGroupSessions(gridList);
	}
	
	public void applyFilter() {
		applyFilter(this.searchBean);
	}
	
	public void applyFilter(GroupSessionSearchDTO searchBean) {
		presenter.applyFilter(searchBean);
	}
	
//	public void setPatients(List<PersonDTO> patients) {
//		patientComboBox.setItems(patients);
//	}
//	
//	public void setTherapists(List<TherapistDTO> therapists) {
//		therapistComboBox.setItems(therapists);
//	}
	
	public interface ListGroupSessionListener {
		
		void applyFilter(GroupSessionSearchDTO searchBean);
		
		void delete(GroupSessionGridDTO dto);
		
	}
	
	public interface ListGroupSessionModel extends TemplateModel {
		
		@Include({ "id", "startDate", "patients", "therapists" })
		@Encode(value = IntegerToStringEncoder.class, path = "id")
		void setGroupSessions(List<GroupSessionGridDTO> groupSessions);
		
		List<GroupSessionGridDTO> getGroupSessions();
		
	}
	
}
