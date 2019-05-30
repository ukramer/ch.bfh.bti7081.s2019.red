package ch.bfh.red.ui.encoders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.flow.templatemodel.ModelEncoder;

public class DateToDateBeanEncoder implements ModelEncoder<DateTimeBean, String> {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	@Override
	public String encode(DateTimeBean modelValue) {
		return String.format("%s_%s", modelValue.getDate(), modelValue.getTime());
	}

	@Override
	public DateTimeBean decode(String presentationValue) {
		Pattern pattern = Pattern.compile("(.*)_(.*)");
		Matcher matcher = pattern.matcher(presentationValue);
		
		if (matcher.find()) {
			String date = matcher.group(1);
			String time = matcher.group(2);
			return new DateTimeBean(date, time);
		}
		
		return new DateTimeBean(new Date());
	}
	
}
