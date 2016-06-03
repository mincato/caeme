package com.caeme.activityreport.core.comparators;

import java.util.Comparator;
import java.util.Date;

import com.caeme.activityreport.core.util.DateUtil;

public class MonthComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		Date date1 = DateUtil.transformMonth(o1);
		Date date2 = DateUtil.transformMonth(o2);
		return date1.compareTo(date2);
	}

}
