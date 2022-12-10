package com.github.h9lib.consultantutils.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateManipulator {
	/**
	 * It creates a Date from a String
	 * 
	 * @param date
	 * @param pattern
	 * @return the Date passed as a String
	 * @throws ParseException
	 */
	public static final Date stringToDate(String date, String pattern) throws ParseException {
		SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		sdf.applyLocalizedPattern(pattern);
		return sdf.parse(date);
	}

	/**
	 * Transform a Date into a String, based on the passed date-pattern
	 * 
	 * @param date
	 * @param pattern can be null
	 * @return the date as a string
	 */
	public static final String dateToString(Date date, String pattern) {
		if(StringUtils.isEmpty(pattern)) 
			return date.toString();

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * It converts a String with specified format-style into a Timestamp object
	 * 
	 * @param date
	 * @param format
	 * @return Timestamp
	 */
	public static final Timestamp stringToTimestamp(String date, String format) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date parsedDate = dateFormat.parse(date);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * It converts a Date into LocalDateTime
	 * 
	 * @param date
	 * @return LocalDateTime with systemDefault ZoneId
	 */
    public static final LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * It converts a LocalDateTime into a Date.
     * 
     * @param localDateTime
     * @return the converted Date.
     */
    public static final Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
