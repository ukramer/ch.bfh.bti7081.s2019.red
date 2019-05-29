package ch.bfh.red.ui.encoders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.flow.templatemodel.ModelEncoder;

public class DateToDateBeanEncoder implements ModelEncoder<DateTimeBean, String> {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	
	@Override
	public String encode(DateTimeBean modelValue) {
		System.out.println("encode: " +modelValue);
		
		if (modelValue == null)
			return null;
		DateTimeBean bean = new DateTimeBean();
		bean.setDate(dateFormat.format(modelValue));
		bean.setTime(timeFormat.format(modelValue));
		
		System.out.println(bean.getDate());
		return bean.getDate();
	}

	@Override
	public DateTimeBean decode(String presentationValue) {
		System.out.println("decode: " +presentationValue);
		
		if (presentationValue == null)
			return null;

		
		return new DateTimeBean();
//		return presentationValue.toDate();
//		throw new UnsupportedOperationException();
	}
	
}
