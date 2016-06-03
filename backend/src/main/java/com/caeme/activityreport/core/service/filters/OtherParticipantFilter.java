package com.caeme.activityreport.core.service.filters;

import org.springframework.stereotype.Component;

import com.caeme.activityreport.core.model.QActivity;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.mysema.query.types.expr.BooleanExpression;

@Component
public class OtherParticipantFilter implements ActivityFilter{

	@Override
	public boolean apply(ActivitySearchRequest activitySearchRequest) {
		return activitySearchRequest.getOther_participant() != null;
	}

	@Override
	public BooleanExpression filter(ActivitySearchRequest activitySearchRequest, BooleanExpression predicate) {
		BooleanExpression finalPredicate = QActivity.activity.otherParticipants.isNotEmpty().and(QActivity.activity.otherParticipants.any().name.containsIgnoreCase(activitySearchRequest.getOther_participant()));
		if (predicate != null) {
			finalPredicate = predicate.and(finalPredicate);
		}
		return finalPredicate;
	}

}
