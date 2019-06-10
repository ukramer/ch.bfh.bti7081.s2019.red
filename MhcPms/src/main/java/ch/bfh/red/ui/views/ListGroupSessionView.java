package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
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
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.dto.GroupSessionDTO;
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

	private Binder<GroupSessionSearchDTO> binder = new Binder<>();
	
	private GroupSessionSearchDTO searchBean = new GroupSessionSearchDTO();
	
	@Autowired
	public ListGroupSessionView(GroupSessionPresenter presenter) {
		this.presenter = presenter;
		
		patientComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		therapistComboBox.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
		
		binder.forField(patientComboBox)
        .bind(GroupSessionSearchDTO::getPatient, GroupSessionSearchDTO::setPatient);
		
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
	}
	
	@Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        presenter.setView(this);

        // init view elements
        header.setText("Gruppensitzungen");
        startDatePicker.setI18n(MainLayout.datePickerI18n);
        endDatePicker.setI18n(MainLayout.datePickerI18n);
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
		
		@Include({ "id", "startDate", "finished"})
		@Encode(value = IntegerToStringEncoder.class, path = "id")
		@Encode(value = DateToStringEncoder.class, path = "startDate")
		void setGroupSessions(List<GroupSessionDTO> groupSessions);
		
		List<GroupSessionDTO> getGroupSessions();
		
	}
	
	public void setGroupSessions(List<GroupSessionDTO> groupSessions) {
		getModel().setGroupSessions(groupSessions);
		
	}
	
}
