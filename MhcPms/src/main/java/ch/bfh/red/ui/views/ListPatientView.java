package ch.bfh.red.ui.views;

import ch.bfh.red.MainLayout;
import ch.bfh.red.backend.models.Address;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.ui.encoders.LongToStringEncoder;
import ch.bfh.red.ui.presenters.PatientPresenter;
import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

@Route(value = "listpatient", layout = MainLayout.class)
@Tag("listpatient-element")
@HtmlImport("frontend://src/views/person/listPatient.html")
public class ListPatientView extends PolymerTemplate<ListPatientView.ListPatientModel> implements View<ListPatientView.ListPatientViewListener> {
    List<ListPatientViewListener> listeners = new ArrayList<>();
    private List<Patient> patientList = new ArrayList<>();

    @Id("header")
    private H2 header;

    public interface ListPatientViewListener {
        void onPatientClick(String param);
    }

    public interface ListPatientModel extends TemplateModel {
        @Include({"firstName", "lastName", "address.street", "address.streetNumber", "address.postalCode", "address.city"})
        @Encode(value = LongToStringEncoder.class, path = "id")
        void setPatienten(List<Patient> patienten);

        List<Patient> getPatienten();

        void setPatientSearchBean(PatientSearchBean patientSearchBean);

        PatientSearchBean getPatientSearchBean();
    }

    @Override
    public void addListener(ListPatientViewListener listener) {
        listeners.add(listener);
    }

    ListPatientView() {
        new PatientPresenter(this);
        header.setText("List Patient");
        patientList.add(new Patient("cyrill", "meyer", new Address("bethlehem", "7", 3185, "schmitten")));
        patientList.add(new Patient("ueli", "kramer", new Address("thunstrasse", "18", 2499, "thun")));
        getModel().setPatienten(patientList);
        getModel().setPatientSearchBean(new PatientSearchBean());
    }

    @EventHandler
    public void edit(@RepeatIndex int itemIndex) {
        //String param = patientList.get(itemIndex).getId()+"";
        String param = itemIndex + "";
        listeners.forEach(x -> {
            x.onPatientClick(param);
        });
        patientList.remove(itemIndex);
    }
}
