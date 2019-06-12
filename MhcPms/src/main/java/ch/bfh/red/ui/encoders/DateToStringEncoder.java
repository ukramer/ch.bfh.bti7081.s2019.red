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

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //Spotbugs: Threadsafety can be ignored, because we don't run more than one thread

    @Override
    public Date decode(String presentationValue) {
        try {
            return format.parse(presentationValue); //SpotBugs: Only applies to Multithreading
        } catch (ParseException e) {}
        return null;
    }

    @Override
    public String encode(Date modelValue) {
        return modelValue == null ? null : format.format(modelValue); //SpotBugs: Only applies to Multithreading
    }

}
