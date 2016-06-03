package com.caeme.activityreport.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caeme.activityreport.core.service.filters.ActivityFilter;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.mysema.query.types.expr.BooleanExpression;

@Component
public class ActivityFilteringImpl implements ActivityFiltering{

	@Autowired
	private List<ActivityFilter> acitivityFilters;
	
	@Override
	public BooleanExpression filter(ActivitySearchRequest activitySearchRequest) {
		BooleanExpression predicate = null;
		for (ActivityFilter activityFilter : acitivityFilters) {
			if (activityFilter.apply(activitySearchRequest)) {
				predicate = activityFilter.filter(activitySearchRequest, predicate);
			}
		}
		return predicate;
	}

	
	
}
