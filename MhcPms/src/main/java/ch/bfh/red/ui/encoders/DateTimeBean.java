package ch.bfh.red.ui.encoders;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeBean implements Serializable {
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	private String date;
	
    private String time;

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
    
}
