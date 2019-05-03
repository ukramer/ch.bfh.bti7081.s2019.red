package ch.bfh.red;

import ch.bfh.red.ui.components.NavigationComponent;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Theme(value = Material.class, variant = Material.LIGHT)
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@StyleSheet("frontend://styles/styles.css")
public class MainLayout extends Div implements RouterLayout, PageConfigurator {
    MainLayout() {
        setMaxWidth("50em");
        addComponentAsFirst(new NavigationComponent());
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
