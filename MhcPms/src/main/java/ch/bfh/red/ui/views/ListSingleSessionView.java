package ch.bfh.red.ui.views;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.dto.GroupSessionDTO;
import ch.bfh.red.ui.dto.GroupSessionGridDTO;
import ch.bfh.red.ui.dto.PatientDTO;
import ch.bfh.red.ui.dto.SingleSessionDTO;
import ch.bfh.red.ui.dto.SingleSessionSearchDTO;
import ch.bfh.red.ui.dto.TherapistDTO;
import ch.bfh.red.ui.encoders.AcademicTitleToStringEncoder;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.SingleSessionPresenter;

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

    @Id("therapist")
    private ComboBox<TherapistDTO> therapistComboBox;
    
    @Id("patient")
    private ComboBox<PatientDTO> patientComboBox;

    private Binder<SingleSessionSearchDTO> binder = new Binder<>();

    private SingleSessionSearchDTO searchBean = new SingleSessionSearchDTO();

    private boolean detectChanges = false;
    
    @Autowired
    public ListSingleSessionView(SingleSessionPresenter presenter) {
        this.presenter = presenter;

        patientComboBox.setDataProvider(DataProvider.ofCollection(new HashSet<>()));
        therapistComboBox.setDataProvider(DataProvider.ofCollection(new HashSet<>()));

        binder.forField(patientComboBox)
                .bind(SingleSessionSearchDTO::getPatient, SingleSessionSearchDTO::setPatient);
        binder.forField(therapistComboBox)
        .bind(SingleSessionSearchDTO::getTherapist, SingleSessionSearchDTO::setTherapist);


        patientComboBox.addValueChangeListener(event -> {
        	if (detectChanges) {
            PatientDTO dto = event.getValue();
            this.searchBean.setPatient(dto);
            applyFilter();
        	}
        });
        
        therapistComboBox.addValueChangeListener(event -> {
        	if (detectChanges) {
            TherapistDTO dto = event.getValue();
            this.searchBean.setTherapist(dto);
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
        header.setText("Einzelsitzungen");
        startDatePicker.setI18n(MainLayout.datePickerI18n);
        endDatePicker.setI18n(MainLayout.datePickerI18n);
    }
    
    @EventHandler
    public void add() {
        UI.getCurrent().navigate(EditSingleSessionView.class);
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

    public void setSingleSessions(List<SingleSessionDTO> dtos) {
    	HashSet<PatientDTO> patientsDTO = new HashSet<>();
		for (SingleSessionDTO dto : dtos)
			patientsDTO.add(dto.getPatient());
		patientComboBox.setItems(patientsDTO);
		
		HashSet<TherapistDTO> therapistsDTO = new HashSet<>();
		for (SingleSessionDTO dto : dtos)
			therapistsDTO.add(dto.getTherapist());
		therapistComboBox.setItems(therapistsDTO);
		
		setFilteredSessions(dtos);
    }
    
    public void setFilteredSessions(List<SingleSessionDTO> filterDTOs) {
    	getModel().setSingleSessions(filterDTOs);
	}

//    public void setPatients(List<PatientDTO> patients) {
//        patientComboBox.setItems(patients);
//    }

    public void applyFilter() {
        applyFilter(this.searchBean);
    }

    public void applyFilter(SingleSessionSearchDTO searchBean) {
        presenter.applyFilter(searchBean);
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
    
    private void confirmDelete(SingleSessionDTO singleSession) {
        if (singleSession == null)
            return;
        presenter.delete(singleSession);
        Notification.show("Die Einzelsitzun wurde erfolgreich gelöscht.");
        getModel().getSingleSessions().remove(singleSession);
    }

}
