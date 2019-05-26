package ch.bfh.red.ui.views;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.ui.encoders.DateTimeBean;
import ch.bfh.red.ui.encoders.DateToDateBeanEncoder;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.DateToTimeInHoursMinutesStringConverter;
import ch.bfh.red.ui.encoders.PatientToNameStringConverter;
import ch.bfh.red.ui.encoders.TherapistToNameStringConverter;

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
	private TextField therapist;
	
	@Id("patient")
	private TextField patient;
	
	@Id("sessionType")
	private TextField sessionType;
	
	private SingleSession singleSession;
	
	public EditSingleSessionView() {
		header.setText("Edit Single Session");
		
	}
	
	public interface EditSingleSessionListener {
		
	}
	
	
	
	public interface EditSingleSessionModel extends TemplateModel {
		@Include({ 
//			"startDate"
//			, 
//			"startDate", "endDate",
//				"endDate", "sessionType"
//			,
			"patient", "therapist" 
				})
//		@Encode(value = DateToDateBeanEncoder.class, path = "startDate.dateString")
//		@Encode(value = DateToDateBeanEncoder.class, path = "startDate.timeString")
//		@Encode(value = DateToDateBeanEncoder.class, path = "endDate.dateString")
//		@Encode(value = DateToDateBeanEncoder.class, path = "endDate.timeString")
		@Encode(value = PatientToNameStringConverter.class, path = "patient")
		@Encode(value = TherapistToNameStringConverter.class, path = "therapist")
		void setSingleSession(SingleSession singleSession);
		
//		@Include({
//			"startDate"
//		})
		@Encode(value = DateToDateBeanEncoder.class, path = "startDate")
//		@Encode(value = DateToDateBeanEncoder.class, path = "startDate.timeString")
		void setStartDate(DateTimeBean date);
//		
//		void setStartDate()
		
	}
	
	@Override
	public void setListener(EditSingleSessionListener listener) {
		this.listener = listener;
	}
}
