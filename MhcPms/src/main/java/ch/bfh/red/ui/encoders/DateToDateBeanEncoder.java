package ch.bfh.red.ui.encoders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.flow.templatemodel.ModelEncoder;

public class DateToDateBeanEncoder implements ModelEncoder<Date, DateTimeBean> {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	
	@Override
	public DateTimeBean encode(Date modelValue) {
		if (modelValue == null)
			return null;
		DateTimeBean bean = new DateTimeBean(modelValue);
		return bean;
	}

	@Override
	public Date decode(DateTimeBean presentationValue) {
		if (presentationValue == null)
			return null;

		return presentationValue.toDate();
	}
	
}
