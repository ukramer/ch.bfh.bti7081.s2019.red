<<<<<<< HEAD
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
=======
package ch.bfh.red.ui.encoders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.flow.templatemodel.ModelEncoder;

/**
 * Converts between DateTime-objects and their String-representations
 */

public class DateToStringEncoder
        implements ModelEncoder<Date, String> {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");;

    @Override
    public Date decode(String presentationValue) {
        try {
			return  SIMPLE_DATE_FORMAT.parse(presentationValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }

    @Override
    public String encode(Date modelValue) {
        return modelValue == null ? null : SIMPLE_DATE_FORMAT.format(modelValue);
    }

}
>>>>>>> implementing ExpositionView (Issue Nr. 6)
