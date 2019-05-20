package ch.bfh.red.ui.common;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.shared.Registration;

import java.io.Serializable;
import java.util.function.BiConsumer;

public abstract class AbstractEditorDialog<T extends Serializable> extends Dialog {
    public enum Operation {
        ADD("erfassen"), EDIT("bearbeiten");

        private final String nameInTitle;

        Operation(String nameInTitle) {
            this.nameInTitle = nameInTitle;
        }

        public String getNameInTitle() {
            return nameInTitle;
        }
    }

    private final H3 titleField = new H3();
    private final Button saveButton = new Button("Speichern");
    private final Button cancelButton = new Button("Abbrechen");
    private Registration registrationForSave;
    private Registration saveShortcutRegistration;

    private final FormLayout formLayout = new FormLayout();
    private final HorizontalLayout buttonBar = new HorizontalLayout(saveButton, cancelButton);

    private Binder<T> binder = new Binder<>();
    private T currentItem;

    private final String itemType;
    private final BiConsumer<T, Operation> itemSaver;

    /**
     * Constructs a new instance.
     *
     * @param itemType
     *            The readable name of the item type
     * @param itemSaver
     *            Callback to save the edited item
     */
    protected AbstractEditorDialog(String itemType, BiConsumer<T, Operation> itemSaver) {
        this.itemType = itemType;
        this.itemSaver = itemSaver;

        initTitle();
        initFormLayout();
        initButtonBar();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
    }

    private void initTitle() {
        Div header = new Div();
        header.getElement().setProperty("part", "header");
        header.add(titleField);
        add(header);
    }

    private void initFormLayout() {
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("25em", 2));

        Div div = new Div(formLayout);
        div.addClassName("has-padding");
        add(div);
    }

    private void initButtonBar() {
        saveButton.setAutofocus(true);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton.addClickListener(e -> close());
        add(buttonBar);
    }

    /**
     * Gets the form layout, where additional components can be added for
     * displaying or editing the item's properties.
     *
     * @return the form layout
     */
    protected final FormLayout getFormLayout() {
        return formLayout;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    protected final Binder<T> getBinder() {
        return binder;
    }

    /**
     * Gets the item currently being edited.
     *
     * @return the item currently being edited
     */
    protected final T getCurrentItem() {
        return currentItem;
    }

    /**
     * Opens the given item for editing in the dialog.
     *
     * @param item
     *            The item to edit; it may be an existing or a newly created
     *            instance
     * @param operation
     *            The operation being performed on the item
     */
    public final void open(T item, Operation operation) {
        currentItem = item;
        titleField.setText(itemType + " " + operation.getNameInTitle());
        if (registrationForSave != null) {
            registrationForSave.remove();
        }
        registrationForSave = saveButton.addClickListener(e -> saveClicked(operation));
        binder.readBean(currentItem);

        enableShortcuts();
        open();
    }

    private void saveClicked(Operation operation) {
        boolean isValid = binder.writeBeanIfValid(currentItem);

        if (isValid) {
            itemSaver.accept(currentItem, operation);
            close();
        } else {
            BinderValidationStatus<T> status = binder.validate();
        }
    }

    @Override
    public void close() {
        super.close();
        disableShortcuts();
    }

    private void enableShortcuts() {
        disableShortcuts();
        saveShortcutRegistration = saveButton.addClickShortcut(Key.ENTER);
    }

    private void disableShortcuts() {
        if (saveShortcutRegistration != null) {
            saveShortcutRegistration.remove();
            saveShortcutRegistration = null;
        }
    }
}
