package ch.bfh.red.ui.views;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.services.TherapistService;
import ch.bfh.red.ui.encoders.LongToStringEncoder;
import ch.bfh.red.MainLayout;
import ch.bfh.red.ui.presenters.DashboardPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "", layout = MainLayout.class)
@Tag("custom-dashboard-element")
@HtmlImport("frontend://src/views/dashboard/dashboard.html")
public class DashboardView extends PolymerTemplate<DashboardView.TherapistModel> implements View<DashboardView.DashboardViewListener> {
    /** View Model Interface **/
    public interface TherapistModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        void setTherapist(List<Therapist> therapist);
    }

    /** Html Elements **/
    @Id("header")
    private H2 header;

    /** Listener Interface **/
    public interface DashboardViewListener {

    }

    /** Helpers and Services **/
    private TherapistService therapistService;

    public DashboardView(@Autowired TherapistService therapistService) {
        new DashboardPresenter(this);
        this.therapistService = therapistService;
        getModel().setTherapist(null); // otherwise pass the model
        header.setText("Dashboard");
    }

    /* Only the presenter registers one listener... */
    private List<DashboardViewListener> listeners = new ArrayList<>();

    @Override
    public void addListener(DashboardViewListener listener) {
        listeners.add(listener);
    }

    // example event, delegate the event to the listeners
    /*
    private void buttonClicked(ClickEvent clickEvent) {
        listeners.forEach(l -> l.buttonNewTask(clickEvent));
    }
    */
}
