package ch.bfh.red.ui.views;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Exclude;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.backend.services.ExpositionNoteService;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.presenters.ExpositionPresenter;


@Route(value="exposition", layout= MainLayout.class)
@Tag("exposition-view")
@HtmlImport("frontend://src/views/therapy/exposition-view.html")

public class ExpositionView extends PolymerTemplate<ExpositionView.ExpositionViewModel> implements View<ExpositionView.ExpositionViewListener> {
	 
//	private @Id("expositionColumn") Grid.Column<String> expositionColumn;
//	private	@Id("dateColumn") Grid.Column<Date> dateColumn;
//	private @Id("add-exp-btn") Button addExpositionButton;
//	private @Id("vaadinGrid-expositions") Grid<ExpositionNote> expositionGrid;
 	
	public interface ExpositionViewModel extends TemplateModel {
		    @Include({"date", "text", "degreeOfExposure"})
	        @Exclude("visibility")
	        @Encode(value = DateToStringEncoder.class, path = "date")
	        void setExposition(ExpositionNote exposition);
	        ExpositionNote getExposition();
	    }
	  
   public interface ExpositionViewListener{
	   
   }
   
  private ExpositionNoteService expositionService;
  
    public ExpositionView(@Autowired ExpositionNoteService expositionService) {
	   new ExpositionPresenter(this);
	   this.expositionService = expositionService;
  }
   
   @Override
   public void addListener(ExpositionViewListener listener) {
	   listeners.add(listener);
   }
   
   private List<ExpositionViewListener> listeners = new ArrayList<>();
     
   
   /* Creates a new ExpositionView.
     */
    public ExpositionView() {

    	new ExpositionPresenter(this);
   	
       ArrayList<ExpositionNote> myExpositions = new ArrayList();
       ExpositionNote exp1 = new ExpositionNote( new Date(),"Used public transport without washing hands after", new Visibility(), 8);
       ExpositionNote  exp2 = new ExpositionNote(new Date(), "Went to bed without washing ritual", new Visibility(), 9);
       getModel().setExposition(exp1);
       getModel().setExposition(exp2);
    }
    
    
    @EventHandler
    private void createExposition() {
    	System.out.println("Adding a new exposition");
    }

    
}
