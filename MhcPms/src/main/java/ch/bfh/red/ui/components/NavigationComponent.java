package ch.bfh.red.ui.components;

import ch.bfh.red.ui.views.DashboardView;
import ch.bfh.red.ui.views.EditGroupSessionView;
import ch.bfh.red.ui.views.EditPatientView;
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
        add(new RouterLink("Dashboard", DashboardView.class));
        add(new RouterLink("List Patient", ListPatientView.class));
        add(new RouterLink("Edit Patient", EditPatientView.class));
        add(new RouterLink("Edit Group Session", EditGroupSessionView.class));
        add(new RouterLink("Test2", DashboardView.class));
      
        getElement().getClassList().add("main-layout__nav");

        RouterLink dashboard = new RouterLink(null, DashboardView.class);
        dashboard.add(new Icon(VaadinIcon.LIST), new Text("Dashboard"));
        dashboard.addClassName("main-layout__nav-item");
        dashboard.setHighlightCondition(HighlightConditions.sameLocation());
        add(dashboard);

        RouterLink patientList = new RouterLink(null, ListPatientView.class);
        patientList.add(new Icon(VaadinIcon.LIST), new Text("Patient list"));
        patientList.addClassName("main-layout__nav-item");
        patientList.setHighlightCondition(HighlightConditions.sameLocation());
        add(patientList);
      
    }

    private void add(Component child) {
        getElement().appendChild(child.getElement());
    }
}
