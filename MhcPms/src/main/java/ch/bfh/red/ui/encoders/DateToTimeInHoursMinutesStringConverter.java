package ch.bfh.red.ui.encoders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.flow.templatemodel.ModelEncoder;

public class DateToTimeInHoursMinutesStringConverter implements ModelEncoder<Date, String>{
	
	public static final SimpleDateFormat format = new SimpleDateFormat("HH:mm");

	@Override
	public String encode(Date modelValue) {
		if (modelValue == null)
			return null;
		return format.format(modelValue);
	}

	@Override
	public Date decode(String presentationValue) {
		if (presentationValue == null)
			return null;
		try {
            return format.parse(presentationValue);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
		return null;
	}
	
}
