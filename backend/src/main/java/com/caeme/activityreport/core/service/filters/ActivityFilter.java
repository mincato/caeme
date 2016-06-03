package com.caeme.activityreport.core.service.filters;

import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.mysema.query.types.expr.BooleanExpression;

public interface ActivityFilter {

	boolean apply(ActivitySearchRequest activitySearchRequest);

	BooleanExpression filter(ActivitySearchRequest activitySearchRequest, BooleanExpression predicate);

	
	
}
