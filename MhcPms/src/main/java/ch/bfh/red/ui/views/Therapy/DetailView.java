package ch.bfh.red.ui.views.Therapy;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapy;
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
import com.vaadin.flow.router.*;
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
public class DetailView extends PolymerTemplate<DetailView.TherapyModel> implements HasUrlParameter<Integer>, View<DetailView.DetailViewListener>, BeforeEnterObserver, AfterNavigationObserver {
    private DetailViewListener listener;

    @Id("header")
    private H2 header;

    @Id("startDate")
    private DatePicker startDate;

    @Id("finished")
    private Checkbox finished;

    private ConfirmationDialog<Therapy> confirmationDialog = new ConfirmationDialog<>();

    private TherapyPresenter therapyPresenter;

    private Binder<Therapy> binder = new Binder<>();

    DetailView(@Autowired TherapyPresenter therapyPresenter) {
        this.therapyPresenter = therapyPresenter;

        binder.forField(startDate).asRequired("Es muss ein Startdatum gesetzt sein.").bind(Therapy::getStartDateAsLocalDate, Therapy::setStartDateAsLocalDate);
        binder.forField(finished).bind(Therapy::isFinished, Therapy::setFinished);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer integer) {
        therapyPresenter.setView(this);

        if (integer == null) {
            listener.prepareNewObject();
        } else {
            try {
                listener.load(integer);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        startDate.setI18n(MainLayout.datePickerI18n);
    }

    @Override
    public void setListener(DetailViewListener listener) {
        this.listener = listener;
    }

    @EventHandler
    public void delete() {
        confirmationDialog.open(
                "Therapie wirklich löschen?",
                "Möchten Sie die Therapie wirklich löschen?", "", "Löschen",
                true, getModel().getTherapy(), this::confirmDelete);
    }

    public void confirmDelete(Therapy therapy) {
        listener.delete(therapy);
        Notification.show("Die Therapie wurde erfolgreich gelöscht.");
        UI.getCurrent().navigate(ListView.class);
    }

    @EventHandler
    public void save() {
        BinderValidationStatus<Therapy> validate = binder.validate();
        if (validate.isOk()) {
            try {
                listener.save(binder.getBean());
                Notification.show("Die Therapie wurde erfolgreich aktualisiert.");
            } catch (Exception e) {
                Notification.show(e.getMessage());
            }
        } else {
            List<String> errorMessages = new ArrayList<>();
            validate.getValidationErrors().forEach(e -> errorMessages.add(e.getErrorMessage()));
            String errorMessage = errorMessages.stream().collect(Collectors.joining("<br>"));
            Notification.show(errorMessage);
        }
    }

    public void setTherapy(Therapy therapy) {
        binder.setBean(therapy);
        if (therapy.getId() == 0) {
            header.setText("Neue Therapie");
        } else {
            header.setText("Therapie #" + therapy.getId());
        }
        getModel().setTherapy(therapy);
    }

    public void setSingleSessions(List<SingleSession> singleSessions) {
        getModel().setSingleSessions(singleSessions);
    }

    public void setGroupSessions(List<GroupSession> groupSessions) {
        getModel().setGroupSessions(groupSessions);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        if (binder.getBean() == null) {
            Notification.show("Die aufgerufene Therapie konnte nicht gefunden werden.");
            UI.getCurrent().navigate(ListView.class);
        }
    }

    public interface DetailViewListener {
        void delete(Therapy therapy);

        void load(Integer therapyId);

        void save(Therapy therapy) throws Exception;

        void prepareNewObject();
    }

    public interface TherapyModel extends TemplateModel {
        Therapy getTherapy();

        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        @Include({"id", "startDate", "finished"})
        void setTherapy(Therapy therapy);

        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        @Encode(value = DateToStringEncoder.class, path = "endDate")
        @Include({"id", "startDate", "endDate", "sessionType.name", "patient.firstName", "patient.lastName", "therapist.academicTitle.prefix", "therapist.firstName", "therapist.lastName"})
        void setSingleSessions(List<SingleSession> singleSessions);

        @Encode(value = IntegerToStringEncoder.class, path = "id")
        @Encode(value = DateToStringEncoder.class, path = "startDate")
        @Encode(value = DateToStringEncoder.class, path = "endDate")
        @Include({"id", "startDate", "endDate", "sessionType.name", "patients.firstName", "patients.lastName", "therapists.academicTitle.prefix", "therapists.firstName", "therapists.lastName"})
        void setGroupSessions(List<GroupSession> groupSessions);
    }
}
