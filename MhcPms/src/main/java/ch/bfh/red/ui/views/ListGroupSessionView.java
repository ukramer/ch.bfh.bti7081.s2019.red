package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.dto.GroupSessionDTO;
import ch.bfh.red.ui.dto.GroupSessionGridDTO;
import ch.bfh.red.ui.dto.GroupSessionSearchDTO;
import ch.bfh.red.ui.dto.PersonDTO;
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
	private ComboBox<PersonDTO> patientComboBox;
	
	@Id("therapist")
	private ComboBox<TherapistDTO> therapistComboBox;
	
	@Id("startDate.date")
	private DatePicker startDatePicker;
	
	@Id("endDate.date")
	private DatePicker endDatePicker;
	
	@Id("groupSessionGrid")
	private Grid<GroupSessionGridDTO> grid;
	
	private Binder<GroupSessionSearchDTO> binder = new Binder<>();
	
	private GroupSessionSearchDTO searchBean = new GroupSessionSearchDTO();
	
	@Autowired
	public ListGroupSessionView(GroupSessionPresenter presenter) {
		this.presenter = presenter;
		
		patientComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		therapistComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		
		binder.forField(patientComboBox)
				.bind(GroupSessionSearchDTO::getPatient,
						GroupSessionSearchDTO::setPatient);
		
		patientComboBox.addValueChangeListener(event -> {
			PersonDTO patientSearchBean = event.getValue();
			this.searchBean.setPatient(patientSearchBean);
			applyFilter();
		});
		
		therapistComboBox.addValueChangeListener(event -> {
			TherapistDTO patientSearchBean = event.getValue();
			PersonDTO personDTO = new PersonDTO();
			if (patientSearchBean != null) {
				personDTO.setFirstName(patientSearchBean.getFirstName());
				personDTO.setLastName(patientSearchBean.getLastName());
			}
			this.searchBean.setTherapist(personDTO);
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
		
//		grid.setSizeFull();
		Column<GroupSessionGridDTO> startDateColumn = grid.addColumn(dto -> dto.getStartDate());
		startDateColumn.setHeader("Startdatum");
		startDateColumn.setSortable(true);
		startDateColumn.setWidth("100px");
		startDateColumn.setFlexGrow(5);
		Column<GroupSessionGridDTO> patientsColumn = grid.addColumn(dto -> dto.getPatients());
		patientsColumn.setHeader("Patienten");
		patientsColumn.setSortable(true);
		patientsColumn.setFlexGrow(10);
		Column<GroupSessionGridDTO> therapistsColumn = grid.addColumn(dto -> dto.getTherapists());
		therapistsColumn.setHeader("Therapeuten");
		therapistsColumn.setSortable(true);
		therapistsColumn.setFlexGrow(10);
		
		Icon icon = new Icon(VaadinIcon.EDIT);
		icon.setSize("20px");
		icon.getStyle().set("float", "left");
		icon.setColor("blue");
		Label nameLabel = new Label();
		nameLabel.add(icon);
		
		grid.setWidth("1100px");
		
		GridContextMenu<GroupSessionGridDTO> contextMenu = grid.addContextMenu();
		contextMenu.addItem("Bearbeiten", event -> {
			Optional<GroupSessionGridDTO> dto = event.getItem();
			if (dto.isPresent()) {
				System.out.println("Element ausgewählt : ) id = " +dto.get().getId());
			}
		});
		contextMenu.addItem("Löschen", event -> {
			Optional<GroupSessionGridDTO> dto = event.getItem();
			if (dto.isPresent()) {
				System.out.println("Element löschen : ) id = " +dto.get().getId());
			}
		});
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		presenter.setView(this);
		
		// init view elements
		header.setText("Gruppensitzungen");
		startDatePicker.setI18n(MainLayout.datePickerI18n);
		endDatePicker.setI18n(MainLayout.datePickerI18n);
	}
	
	public void setGroupSessions(List<GroupSessionDTO> groupSessions) {
		List<GroupSessionGridDTO> gridList = new ArrayList<>();
		DateToStringEncoder encoder = new DateToStringEncoder();
		for (GroupSessionDTO groupSession : groupSessions) {
			Integer id = groupSession.getId();
			String startDate = encoder.encode(groupSession.getStartDate());
			String patients = groupSession.getPatients().stream()
					.map(p -> p.getLastName()).collect(Collectors.joining(", "));
			String therapists = groupSession.getTherapists().stream().map(t -> t.getLastName())
					.collect(Collectors.joining(", "));
			gridList.add(new GroupSessionGridDTO(id, startDate, patients, therapists));
		}
		
		
//		getModel().setGroupSessions(gridList);
		grid.setItems(gridList);
		
	}
	
	public void applyFilter() {
		applyFilter(this.searchBean);
	}
	
	public void applyFilter(GroupSessionSearchDTO searchBean) {
		presenter.applyFilter(searchBean);
	}
	
	public void setPatients(List<PersonDTO> patients) {
		patientComboBox.setItems(patients);
	}
	
	public void setTherapists(List<TherapistDTO> therapists) {
		therapistComboBox.setItems(therapists);
	}
	
	public interface ListGroupSessionListener {
		
		void applyFilter(GroupSessionSearchDTO searchBean);
		
	}
	
	public interface ListGroupSessionModel extends TemplateModel {
		
//		@Include({ "id", "startDate", "patients", "therapists" })
//		@Encode(value = IntegerToStringEncoder.class, path = "id")
//		void setGroupSessions(List<GroupSessionGridDTO> groupSessions);
//		
//		List<GroupSessionGridDTO> getGroupSessions();
		
	}
	
}
