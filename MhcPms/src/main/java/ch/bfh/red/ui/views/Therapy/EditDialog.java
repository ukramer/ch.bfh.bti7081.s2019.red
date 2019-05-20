package ch.bfh.red.ui.views.Therapy;

import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.services.TherapyService;
import ch.bfh.red.ui.common.AbstractEditorDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.BiConsumer;

public class EditDialog extends AbstractEditorDialog<Therapy> {
    @Autowired
    private TherapyService therapyService;

    private DatePicker startDate = new DatePicker();

    public EditDialog(BiConsumer<Therapy, Operation> saveHandler) {
        super("Therapie", saveHandler);

        createDatePicker();
    }

    private void createDatePicker() {
        startDate.setLabel("Startdatum");
        startDate.setRequired(true);
        getFormLayout().add(startDate);

        getBinder().forField(startDate).bind(Therapy::getStartDateAsLocalDate, Therapy::setStartDateAsLocalDate);
    }
}

