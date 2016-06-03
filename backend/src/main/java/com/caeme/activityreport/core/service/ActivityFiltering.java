package com.caeme.activityreport.core.service;

import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.mysema.query.types.expr.BooleanExpression;

public interface ActivityFiltering {

	BooleanExpression filter(ActivitySearchRequest activitySearchRequest);

}
