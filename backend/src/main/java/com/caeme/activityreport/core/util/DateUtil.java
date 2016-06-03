package com.caeme.activityreport.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String transformMonth(final Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy");
		return sdf.format(date);
	}

	public static Date transformMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
}
