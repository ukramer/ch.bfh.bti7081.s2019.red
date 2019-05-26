package ch.bfh.red.ui.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

import java.util.function.Consumer;

public class ConfirmationDialog<T> extends Dialog {
    private final H3 titleField = new H3();
    private final Div messageLabel = new Div();
    private final Div extraMessageLabel = new Div();
    private final Button confirmButton = new Button();
    private final Button cancelButton = new Button("Abbrechen");
    private Registration registrationForConfirm;
    private Registration shortcutRegistrationForConfirm;

    public ConfirmationDialog() {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);

        confirmButton.addClickListener(e -> close());
        confirmButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        confirmButton.setAutofocus(true);
        cancelButton.addClickListener(e -> close());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        HorizontalLayout buttonBar = new HorizontalLayout(confirmButton, cancelButton);
        buttonBar.setClassName("buttons confirm-buttons");

        Div labels = new Div(messageLabel, extraMessageLabel);
        labels.setClassName("confirm-text");

        titleField.setClassName("confirm-title");

        add(titleField, labels, buttonBar);
    }

    public void open(String title, String message, String additionalMessage,
                     String actionName, boolean isDisruptive, T item,
                     Consumer<T> confirmHandler) {
        titleField.setText(title);
        messageLabel.setText(message);
        extraMessageLabel.setText(additionalMessage);
        confirmButton.setText(actionName);

        shortcutRegistrationForConfirm = confirmButton.addClickShortcut(Key.ENTER);
        if (registrationForConfirm != null) {
            registrationForConfirm.remove();
        }
        registrationForConfirm = confirmButton.addClickListener(e -> confirmHandler.accept(item));
        confirmButton.removeThemeVariants(ButtonVariant.LUMO_ERROR);
        if (isDisruptive) {
            confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        }
        open();
    }

    @Override
    public void close() {
        super.close();
        if (shortcutRegistrationForConfirm != null) {
            shortcutRegistrationForConfirm.remove();
        }
    }
}
