package ch.bfh.red;

import ch.bfh.red.ui.components.NavigationComponent;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

@HtmlImport("frontend://styles/styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div implements RouterLayout, PageConfigurator {
    MainLayout() {
        H2 title = new H2("MhcPms");
        title.addClassName("main-layout__title");

        Div header = new Div(title, new NavigationComponent());
        header.addClassName("main-layout__header");
        add(header);

        addClassName("main-layout");
        setMaxWidth("50em");
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
