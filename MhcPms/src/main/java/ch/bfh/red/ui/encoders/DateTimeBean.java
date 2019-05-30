package ch.bfh.red.ui.encoders;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeBean implements Serializable {
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
//	private int year;
//	
//	private int month;
//	
//	private int day;
//
//	private int minutes;
//	
//	private int hours;
	
	private String date;
	
    private String time;
    
//    private Calendar calendar;

    public DateTimeBean() {}
    
    public DateTimeBean(String date, String time) {
    	this.date = date;
    	this.time = time;
    }

	public DateTimeBean(Date startDate) {
		this.date = dateFormat.format(startDate);
		this.time = timeFormat.format(startDate);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
    
    
    
    
//	public DateTimeBean(Date date) {
//		Calendar calendar = GregorianCalendar.getInstance();
//		calendar.setTime(date);
//		this.calendar = calendar;
//		
//		this.year = calendar.get(Calendar.YEAR);
//		this.month = calendar.get(Calendar.MONTH);
//		this.day = calendar.get(Calendar.DAY_OF_MONTH);
//		this.hours = calendar.get(Calendar.HOUR_OF_DAY);
//		this.minutes = calendar.get(Calendar.MINUTE);
//		
//	}
//	
//	public Date toDate() {
//		return this.calendar.getTime();
//	}
//
//	public int getYear() {
//		return year;
//	}
//
//	public void setYear(int year) {
//		this.year = year;
//	}
//
//	public int getMonth() {
//		return month;
//	}
//
//	public void setMonth(int month) {
//		this.month = month;
//	}
//
//	public int getDay() {
//		return day;
//	}
//
//	public void setDay(int day) {
//		this.day = day;
//	}
//
//	public int getMinutes() {
//		return minutes;
//	}
//
//	public void setMinutes(int minutes) {
//		this.minutes = minutes;
//	}
//
//	public int getHours() {
//		return hours;
//	}
//
//	public void setHours(int hours) {
//		this.hours = hours;
//	}

	
    
    

//	public Calendar getCalendar() {
//		return calendar;
//	}
//
//	public void setCalendar(Calendar calendar) {
//		this.calendar = calendar;
//	}

}
