package com.github.h9lib.consultantutils.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static Date stringToDate(String date, String pattern) throws ParseException {
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
	public static String dateToString(Date date, String pattern) {
		if(StringUtils.isEmpty(pattern)) 
			return date.toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
