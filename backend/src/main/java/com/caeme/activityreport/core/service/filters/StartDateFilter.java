package com.caeme.activityreport.core.service.filters;

import org.springframework.stereotype.Component;

import com.caeme.activityreport.core.model.QActivity;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.mysema.query.types.expr.BooleanExpression;

@Component
public class StartDateFilter implements ActivityFilter{

	@Override
	public boolean apply(ActivitySearchRequest activitySearchRequest) {
		return activitySearchRequest.getStartDate() != null || activitySearchRequest.getEndDate() != null;
	}

	@Override
	public BooleanExpression filter(ActivitySearchRequest activitySearchRequest, BooleanExpression predicate) {
		BooleanExpression finalPredicate = null;
		if (activitySearchRequest.getStartDate() != null && activitySearchRequest.getEndDate() != null) {
			finalPredicate = QActivity.activity.startDate.between(activitySearchRequest.getStartDate(), activitySearchRequest.getEndDate());			
		} else if (activitySearchRequest.getStartDate() != null) {
			finalPredicate = QActivity.activity.startDate.after(activitySearchRequest.getStartDate());
		} else {
			finalPredicate = QActivity.activity.startDate.before(activitySearchRequest.getEndDate());
		}
		if (predicate != null) {
			finalPredicate = predicate.and(finalPredicate);
		}
		return finalPredicate;
	}

}
