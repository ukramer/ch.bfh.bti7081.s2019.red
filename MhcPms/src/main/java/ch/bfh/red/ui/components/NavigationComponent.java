package ch.bfh.red.ui.components;

import ch.bfh.red.ui.views.DashboardView;
import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.RouterLink;

@Tag("div")
public class NavigationComponent extends Component {
    public NavigationComponent() {
        add(new RouterLink("Dashboard", DashboardView.class));
        add(new RouterLink("List Patient", ListPatientView.class));
        add(new RouterLink("Edit Patient", EditPatientView.class));
        add(new RouterLink("Test2", DashboardView.class));
    }

    private void add(Component child) {
        Element childWrapper = ElementFactory.createDiv();
        childWrapper.appendChild(child.getElement());
        getElement().appendChild(childWrapper);
    }
}
