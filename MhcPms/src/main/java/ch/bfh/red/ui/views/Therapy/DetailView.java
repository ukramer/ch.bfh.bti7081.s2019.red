package ch.bfh.red.ui.views.Therapy;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.components.ConfirmationDialog;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.TherapyPresenter;
import ch.bfh.red.ui.views.View;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "therapy/detail", layout = MainLayout.class)
@Tag("therapy-detail")
@HtmlImport("frontend://src/views/therapy/detail.html")
@Component
@UIScope
public class DetailView extends PolymerTemplate<DetailView.TherapyModel> implements HasUrlParameter<Integer>, View<DetailView.DetailViewListener> {
    private List<DetailViewListener> listeners = new ArrayList<>();

    @Id("header")
    private H2 header;

    @Id("startDate")
    private DatePicker startDate;

    @Id("finished")
    private Checkbox finished;

    private ConfirmationDialog<Therapy> confirmationDialog = new ConfirmationDialog<>();

    private TherapyPresenter therapyPresenter;

    private TherapyService therapyService;

    private Binder<Therapy> binder = new Binder<>();

    DetailView(@Autowired TherapyService therapyService) {
        startDate.setI18n(MainLayout.datePickerI18n);
        startDate.setRequiredIndicatorVisible(true);
        this.therapyService = therapyService;
        this.therapyPresenter = new TherapyPresenter(this, therapyService);
        binder.forField(startDate).asRequired("Es muss ein Startdatum gesetzt sein.").bind(Therapy::getStartDateAsLocalDate, Therapy::setStartDateAsLocalDate);
        binder.forField(finished).bind(Therapy::isFinished, Therapy::setFinished);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        listeners.forEach(l -> l.load(integer));
    }

    @Override
    public void addListener(DetailViewListener listener) {
        listeners.add(listener);
    }

    @EventHandler
    public void delete() {
        confirmationDialog.open("Therapie wirklich löschen?", "Möchten Sie die Therapie wirklich löschen?", "", "Löschen", true, getModel().getTherapy(), this::confirmDelete);
        ;
    }

    public void confirmDelete(Therapy therapy) {
        listeners.forEach(l -> l.delete(therapy));
        UI.getCurrent().navigate("therapy/list");
    }

    @EventHandler
    public void save() {
        BinderValidationStatus<Therapy> validate = binder.validate();
        if (validate.isOk()) {
            listeners.forEach(l -> l.save(binder.getBean()));
        } else {
            List<String> errorMessages = new ArrayList<>();
            validate.getValidationErrors().forEach(e -> errorMessages.add(e.getErrorMessage()));
            String errorMessage = errorMessages.stream().collect(Collectors.joining("<br>"));
            Notification.show(errorMessage);
        }
    }

    public void setTherapy(Therapy therapy) {
        binder.setBean(therapy);
        header.setText("Therapie #" + therapy.getId());
    }

    public interface DetailViewListener {
        void delete(Therapy therapy);

        void load(Integer therapyId);

        void save(Therapy therapy);
    }

    public interface TherapyModel extends TemplateModel {
        Therapy getTherapy();

        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        @Include({"id", "startDate", "finished"})
        void setTherapy(Therapy therapy);
    }
}
