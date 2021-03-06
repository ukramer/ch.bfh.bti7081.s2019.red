package ch.bfh.red.ui.components;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import ch.bfh.red.ui.views.ExpositionView;
import ch.bfh.red.ui.views.ListPatientView;
import ch.bfh.red.ui.views.ListTherapyView;
import ch.bfh.red.ui.views.ListSingleSessionView;
import ch.bfh.red.ui.views.ListGroupSessionView;

@Tag("div")
public class NavigationComponent extends Component {
    public NavigationComponent() {
        getElement().getClassList().add("main-layout__nav");

        RouterLink patientList = new RouterLink(null, ListPatientView.class);
        patientList.add(new Icon(VaadinIcon.LIST), new Text("Patienten"));
        patientList.addClassName("main-layout__nav-item");
        patientList.setHighlightCondition(HighlightConditions.sameLocation());
        add(patientList);

        RouterLink editGroupSession = new RouterLink(null, ListGroupSessionView.class);
        editGroupSession.add(new Icon(VaadinIcon.PENCIL), new Text("Gruppensitzungen"));
        editGroupSession.addClassName("main-layout__nav-item");
        editGroupSession.setHighlightCondition(HighlightConditions.sameLocation());
        add(editGroupSession);

        RouterLink editSingleSession = new RouterLink(null, ListSingleSessionView.class);
        editSingleSession.add(new Icon(VaadinIcon.PENCIL), new Text("Einzelsitzungen"));
        editSingleSession.addClassName("main-layout__nav-item");
        editSingleSession.setHighlightCondition(HighlightConditions.sameLocation());
        add(editSingleSession);

        RouterLink therapyListView = new RouterLink(null, ListTherapyView.class);
        therapyListView.add(new Icon(VaadinIcon.LIST), new Text("Therapien"));
        therapyListView.addClassName("main-layout__nav-item");
        therapyListView.setHighlightCondition(HighlightConditions.sameLocation());
        add(therapyListView);

        RouterLink expositionView = new RouterLink(null, ExpositionView.class);
        expositionView.add(new Icon(VaadinIcon.LIST), new Text("Expositionen"));
        expositionView.addClassName("main-layout__nav-item");
        expositionView.setHighlightCondition(HighlightConditions.sameLocation());
        add(expositionView);

    }

    private void add(Component child) {
        getElement().appendChild(child.getElement());
    }
}
