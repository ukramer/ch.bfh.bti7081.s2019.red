package ch.bfh.red.ui.encoders;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeBean implements Serializable {
	
	private int year;
	
	private int month;
	
	private int day;

	private int minutes;
	
	private int hours;
	
	private String dateString;
	
    private String timeString;
    
    private Calendar calendar;

    public DateTimeBean() {}
    
	public DateTimeBean(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		this.calendar = calendar;
		
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.hours = calendar.get(Calendar.HOUR_OF_DAY);
		this.minutes = calendar.get(Calendar.MINUTE);
		
	}
	
	public Date toDate() {
		return this.calendar.getTime();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
