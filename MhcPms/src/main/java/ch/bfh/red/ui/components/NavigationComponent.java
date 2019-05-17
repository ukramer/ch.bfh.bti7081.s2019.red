package ch.bfh.red.ui.components;

import ch.bfh.red.ui.views.DashboardView;
import ch.bfh.red.ui.views.EditGroupSessionView;
import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.EditSingleSessionView;
import ch.bfh.red.ui.views.ListPatientView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

@Tag("div")
public class NavigationComponent extends Component {
    public NavigationComponent() {
        getElement().getClassList().add("main-layout__nav");

        RouterLink dashboard = new RouterLink(null, DashboardView.class);
        dashboard.add(new Icon(VaadinIcon.GRID), new Text("Dashboard"));
        dashboard.addClassName("main-layout__nav-item");
        dashboard.setHighlightCondition(HighlightConditions.sameLocation());
        add(dashboard);

        RouterLink patientList = new RouterLink(null, ListPatientView.class);
        patientList.add(new Icon(VaadinIcon.LIST), new Text("Patient list"));
        patientList.addClassName("main-layout__nav-item");
        patientList.setHighlightCondition(HighlightConditions.sameLocation());
        add(patientList);

        RouterLink addPatient = new RouterLink(null, EditPatientView.class);
        addPatient.add(new Icon(VaadinIcon.PLUS), new Text("Add Patient"));
        addPatient.addClassName("main-layout__nav-item");
        addPatient.setHighlightCondition(HighlightConditions.sameLocation());
        add(addPatient);

        RouterLink editGroupSession = new RouterLink(null, EditGroupSessionView.class);
        editGroupSession.add(new Icon(VaadinIcon.PENCIL), new Text("Edit Group Session"));
        editGroupSession.addClassName("main-layout__nav-item");
        editGroupSession.setHighlightCondition(HighlightConditions.sameLocation());
        add(editGroupSession);

        RouterLink editSingleSession = new RouterLink(null, EditSingleSessionView.class);
        editSingleSession.add(new Icon(VaadinIcon.PENCIL), new Text("Edit Single Session"));
        editSingleSession.addClassName("main-layout__nav-item");
        editSingleSession.setHighlightCondition(HighlightConditions.sameLocation());
        add(editSingleSession);
    }

    private void add(Component child) {
        getElement().appendChild(child.getElement());
    }
}
