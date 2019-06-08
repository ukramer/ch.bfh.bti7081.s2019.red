package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.ExpositionPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Route(value = "exposition", layout = MainLayout.class)
@Tag("exposition-view")
@HtmlImport("frontend://src/views/therapy/exposition-view.html")
@Component
@UIScope
public class ExpositionView extends PolymerTemplate<ExpositionView.ExpositionViewModel> implements View<ExpositionView.ExpositionViewListener>, BeforeEnterObserver {
    private ExpositionViewListener listener;

    @Id("header")
    private H2 header;

    private ExpositionPresenter expositionPresenter;

    public interface ExpositionViewModel extends TemplateModel {
        @Include({"patient.firstName","patient.lastName", "text", "date", "degreeOfExposure"})
        @Encode(value = DateToStringEncoder.class, path = "date")
        @Encode(value = IntegerToStringEncoder.class, path = "degreeOfExposure")
        void setExpositions(List<ExpositionNote> expositions);

        List<ExpositionNote> getExpositions();
    }


    public interface ExpositionViewListener {

        void delete(ExpositionNote expositionNote);
        void load(Integer id);
        void save(ExpositionNote expositionNote);


    }

    @Autowired
    public ExpositionView(ExpositionPresenter expositionPresenter) {
        this.expositionPresenter = expositionPresenter;
        expositionPresenter.setView(this);
        // To be removed, preliminary way to set some data
        expositionPresenter.addMockData();


    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent){
        this.expositionPresenter.setView(this);
        header.setText("Expositionen");

    }

    @Override
    public void setListener(ExpositionViewListener listener) {
        this.listener = listener;
    }


    public void setExpositions(List<ExpositionNote> expositions){
        getModel().setExpositions(expositions);
    }

    @EventHandler
    private void createExposition() {
        System.out.println("Adding a new exposition");
    }
}

