package ch.bfh.red.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeUtils {
    
    public static LocalDate toLocalDate(Date date) {
        return toZonedDateTime(date.toInstant())
                .toLocalDate();
    }
    
    public static LocalTime toLocalTime(Date date) {
        return toZonedDateTime(date.toInstant())
                .toLocalTime();
    }
    
    public static LocalDate toLocalDateOrNull(Date date) {
    	if (date == null) return null;
        return toLocalDate(date);
    }
    
    public static LocalTime toLocalTimeOrNull(Date date) {
    	if (date == null) return null;
    	return toLocalTime(date);
    }
    
    public static Date toDate(LocalDate date) {
        return java.util.Date.from(toZonedDateTime(date.atStartOfDay())
                .toInstant());
    }
    
    public static Date toDate(LocalTime time) {
        return toDate(LocalDate.now(), time);
    }
    
    public static Date toDate(LocalDate date, LocalTime time) {
        return toDate(time.atDate(date)
                .atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static Date toDateOrNull(LocalDate date, LocalTime time) {
    	if (date == null) {
    		if (time == null) {
    			return null;
    		} else {
    			return toDate(time);
    		}
    	} else { 
    		if (time == null) {
    			return toDate(date);
    		} else {
    			return toDate(date, time);
    		}
    	}
    }
    
    public static Date toDate(Instant instant) {
        return java.util.Date.from(instant);
    }
    
    public static ZonedDateTime toZonedDateTime(Instant instant) {
        return instant.atZone(ZoneId.systemDefault());
    }
    
    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault());
    }
    
}
