package io.github.hvna9.consultantutils.data;

import io.github.hvna9.consultantutils.commons.Pair;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateManipulator {
	/**
	 * It creates a Date from a String
	 * 
	 * @param date the String that represents the date you want to convert into Date type.
	 * @param pattern
	 * @return the Date passed as a String.
	 * @throws ParseException - If the passed string-date doesn't match with the passed pattern.
	 */
	public static final Date stringToDate(String date, String pattern) throws ParseException {
		SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		sdf.applyLocalizedPattern(pattern);
		return sdf.parse(date);
	}

	/**
	 * It creates a Date from a String, whitout a specified pattern. It automatically retrieves the right format.
	 *
	 * @param date the String that represents the date you want to convert into Date type.
	 * @return the Date passed as a String.
	 * @throws ParseException - If the passed String is not a valid date.
	 */
	public static final Date stringToDate(String date) throws ParseException {
		Pair<Boolean, Date> validatedDate = dateValidator(date);
		if(validatedDate.getLeft())
			return validatedDate.getRight();
		else
			throw new ParseException("Invalid date format", 0);
	}

	/**
	 * Transform a Date into a String, based on the passed date-pattern
	 * 
	 * @param date the object Date you want convert as a String
	 * @param pattern 
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
	 * @param date the String that represents the date you want to convert into Timestamp type
	 * @param pattern 
	 * @return Timestamp
	 */
	public static final Timestamp stringToTimestamp(String date, String pattern) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date parsedDate = dateFormat.parse(date);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * It converts a String without a specified format-style. It evaluates the right format, by passed date as String.
	 *
	 * @param date the String that represents the date you want to convert into Timestamp type
	 * @return Timestamp
	 */
	public static final Timestamp stringToTimestamp(String date) throws ParseException {
		Pair<Boolean, Date> validatedDate = dateValidator(date);
		if(validatedDate.getLeft())
			return new java.sql.Timestamp(validatedDate.getRight().getTime());
		 else
			throw new ParseException("Invalid date format", 0);
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

	/**
	 * This private method is used to validate a String as a Date where is required to get this kind of parsing.
	 *
	 * @param date - The String to validate as a Date
	 * @return
	 */
	private static Pair<Boolean, Date> dateValidator(String date) {
		DateFormat[] validFormats = {
				new SimpleDateFormat("yyyy-MM-dd"),
				new SimpleDateFormat("dd-MM-yyyy"),
				new SimpleDateFormat("yy-MM-dd"),
				new SimpleDateFormat("dd-MM-yy"),
				new SimpleDateFormat("MM/dd/yyyy"),
				new SimpleDateFormat("dd/MM/yyyy"),
				new SimpleDateFormat("yyyy/MM/dd"),
				new SimpleDateFormat("dd-MMM-yyyy"),
				new SimpleDateFormat("MMM dd, yyyy"),
				new SimpleDateFormat("dd/MM/yy"),
				new SimpleDateFormat("MM/dd/yy"),
				new SimpleDateFormat("ddMMyyyy"),
				new SimpleDateFormat("yyyyMMdd"),
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"),
				new SimpleDateFormat("MMM dd, yyyy HH:mm:ss"),
				new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
				new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"),
				new SimpleDateFormat("yy-MM-dd HH:mm:ss"),
				new SimpleDateFormat("dd-MM-yy HH:mm:ss"),
				new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"),
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"),
		};

		boolean isValidFormat = false;
		Date parsedDate = null;

		for(DateFormat format : validFormats) {
			format.setLenient(false);

			try {
				parsedDate = format.parse(date);
				isValidFormat = true;
				break;
			} catch (ParseException ex) {
				isValidFormat = false;
			}
		}

		return new Pair(isValidFormat, parsedDate);
	}
}
