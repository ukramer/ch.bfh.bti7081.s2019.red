package ch.bfh.red.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtils {
	
	public static LocalDate toLocalDate(Date date) {
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
	
	public static Date toDate(LocalDate date) {
		return java.util.Date.from(date.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}
	
	public static Date toDate(LocalTime time) {
		return toDate(LocalDate.now(), time);
	}
	
	public static Date toDate(LocalDate date, LocalTime time) {
		return toDate(time.atDate(date)
				.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date toDate(Instant instant) {
		return java.util.Date.from(instant);
	}
	
}
