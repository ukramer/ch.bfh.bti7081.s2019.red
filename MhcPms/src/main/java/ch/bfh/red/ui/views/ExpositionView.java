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
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
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
public class ExpositionView extends PolymerTemplate<ExpositionView.ExpositionViewModel> implements View<ExpositionView.ExpositionViewListener> {
    private ExpositionViewListener listener;

    public interface ExpositionViewModel extends TemplateModel {
        @Include({"patient.firstName","patient.lastName", "text", "date", "degreeOfExposure"})
        @Encode(value = DateToStringEncoder.class, path = "date")
        @Encode(value = IntegerToStringEncoder.class, path = "degreeOfExposure")
        void setExpositions(List<ExpositionNote> expositions);

        List<ExpositionNote> getExpositions();
    }


    public interface ExpositionViewListener {

    }

    public ExpositionView(@Autowired ExpositionPresenter expositionPresenter) {;
        expositionPresenter.setView(this);
        ArrayList<ExpositionNote> expositions = new ArrayList<>();
        Address address =  new Address("Elisabethenstrasse", "44", 3014, "Bern");

        Patient patient = new Patient("Bruno", "Bernet", address);

        ExpositionNote exp1 = new ExpositionNote(patient, new Date(), "Used public transport without washing hands after", new Visibility("PRIVATE", ""), 8);
        ExpositionNote exp2 = new ExpositionNote(patient,  new Date(), "Went to bed without washing ritual", new Visibility("PRIVATE", ""), 9);
        expositions.add(exp1);
        expositions.add(exp2);
        getModel().setExpositions(expositions);
    }

    @Override
    public void setListener(ExpositionViewListener listener) {
        this.listener = listener;
    }

    @EventHandler
    private void createExposition() {
        System.out.println("Adding a new exposition");
    }
}
