package ch.bfh.red.ui.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts between DateTime-objects and their String-representations
 */

public class DateToStringEncoder
        implements ModelEncoder<Date, String> {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date decode(String presentationValue) {
        try {
            return format.parse(presentationValue);
        } catch (ParseException e) {}
        return null;
    }

    @Override
    public String encode(Date modelValue) {
        return modelValue == null ? null : format.format(modelValue);
    }

}

