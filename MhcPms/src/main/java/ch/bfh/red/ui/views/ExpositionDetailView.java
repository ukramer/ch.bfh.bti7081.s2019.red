package ch.bfh.red.ui.views;


import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.Visibility;
import ch.bfh.red.common.DateTimeUtils;
import ch.bfh.red.ui.encoders.DateToStringEncoder;
import ch.bfh.red.ui.encoders.IntegerToStringEncoder;
import ch.bfh.red.ui.presenters.ExpositionPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Route(value="exposition/detail", layout= MainLayout.class)
@Tag("exposition-detail")
@HtmlImport("frontend://src/views/therapy/exposition-detail.html")
@Component
@UIScope
public class ExpositionDetailView extends PolymerTemplate<ExpositionDetailView.ExpositionDetailModel> implements
        HasUrlParameter<Integer>, View<ExpositionDetailView.ExpositionDetailViewListener>, BeforeEnterObserver,
        AfterNavigationObserver {

    private ExpositionDetailViewListener;
    @Id("header")
    private H2 header;

    @Id("date")
    private DatePicker date;

    @Id("patient")
    private ComboBox<Patient> patient;

    @Id("visibility")
    private ComboBox<Visibility> visibility;

    @Id("text")
    private TextArea text;

    @Id("degree")
    private ComboBox<Integer> degree;

    private ExpositionPresenter expositionPresenter;

    private Binder<ExpositionNote> binder = new Binder<>();

    ExpositionDetailView(@Autowired ExpositionPresenter expositionPresenter) {
        this.expositionPresenter = expositionPresenter;

        //workaround
        visibility.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
        patient.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));
        degree.setDataProvider(DataProvider.ofCollection(new ArrayList<>()));


        binder.forField(date).asRequired("Es muss ein Datum gesetzt sein.")
                .bind(exposition -> DateTimeUtils.toLocalDate(exposition.getDate()),
                        (exposition, localDate) -> exposition.setDate(DateTimeUtils.toDate(localDate)));
        binder.forField(patient).asRequired("Es muss ein Patient gesetzt sein.").bind(ExpositionNote::getPatient, ExpositionNote::setPatient);
        binder.forField(visibility).asRequired("Es muss eine Visibility gesetzt sein.").bind(ExpositionNote::getVisibility, ExpositionNote::setVisibility);
        binder.forField(text).bind(ExpositionNote::getText, ExpositionNote::setText);
        binder.forField(degree).asRequired("Es muss ein Expositionsgrad gesetzt sein.").bind(ExpositionNote::getDegreeOfExposure, ExpositionNote::setDegreeOfExposure);


    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer integer){
        expositionPresenter.setView(this);
        setDegreeItems();

        if(integer==null)
        {
            listener.prepareNewObject();
        }else{
            try{
                listener.load(integer);
            } catch(NoSuchElementException e){
                UI.getCurrent().navigate(ExpositionView.class);
            }
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent){
        date.setI18n(MainLayout.datePickerI18n);
    }

    @Override
    public void setListener(ExpositionDetailViewListener listener){
        this.listener = listener;
    }

    @EventHandler
    public void save(){
        BinderValidationStatus<ExpositionNote> validate = binder.validate();
        if(validate.isOk()){
            try{
                ExpositionNote expositionNote = binder.getBean();
                boolean isNew = expositionNote.getId() == 0;
                listener.save(expositionNote);
                if(isNew){
                    Notification.show("Die Exposition wurde erfolgreich hinzugefügt");
                    UI.getCurrent().navigate(ExpositionView.class);

                }
                else{
                    Notification.show("Die Exposition wurde erfolgreich aktualisiert");
                }
            } catch(Exception e){
                Notification.show(e.getMessage());
            }
        }
        else{
            List<String> errorMessages = new ArrayList<>();
            validate.getValidationErrors().forEach(e ->
                    errorMessages.add(e.getErrorMessage()));
            String errorMessage = errorMessages.stream().collect(Collectors.joining("<br>"));
            Notification.show(errorMessage);

        }
    }

    public void setExposition(ExpositionNote expositionNote){
        binder.setBean(expositionNote);
        if(expositionNote.getId() == 0) {
            header.setText("Neue Exposition");
        }
        else{
            header.setText("Exposition #" + expositionNote.getId());

        }
        getModel().setExposition(expositionNote);
    }



    public void setDegreeItems(){
        degree.setItems( IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()));
    }
    public void setPatients(List<Patient> patients){
        patient.setItems(patients);

    }

    public void setVisibility(List<Visibility> visibilities){
        visibility.setItems(visibilities);

    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent){
        if(binder.getBean()==null){
            Notification.show("Die aufgerufene Exposition konnte nicht gefunden werden.");
            UI.getCurrent().navigate(ExpositionView.class);
        }
    }

    public interface ExpositionDetailViewListener{
        void delete(ExpositionNote expositionNote);
        void load(Integer id);
        void save(ExpositionNote expositionNote) throws Exception;
        void prepareNewObject();
    }

    public interface ExpositionDetailModel extends TemplateModel {

        ExpositionNote getExposition();

        @Encode(value= IntegerToStringEncoder.class, path="id")
        @Encode(value= DateToStringEncoder.class, path="date")
        @Include({"id", "date"})
        void setExposition(ExpositionNote expositionNote);

        @Encode(value= IntegerToStringEncoder.class, path="id")
        @Encode(value= DateToStringEncoder.class, path="date")
        @Encode(value =)
    }

}
