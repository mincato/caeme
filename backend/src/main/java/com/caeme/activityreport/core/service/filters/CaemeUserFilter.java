package com.caeme.activityreport.core.service.filters;

import org.springframework.stereotype.Component;

import com.caeme.activityreport.core.model.QActivity;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.mysema.query.types.expr.BooleanExpression;

@Component
public class CaemeUserFilter implements ActivityFilter{

	@Override
	public boolean apply(ActivitySearchRequest activitySearchRequest) {
		return activitySearchRequest.getCaemeUserIds() != null && !activitySearchRequest.getCaemeUserIds().isEmpty();
	}

	@Override
	public BooleanExpression filter(ActivitySearchRequest activitySearchRequest, BooleanExpression predicate) {
		BooleanExpression finalPredicate = QActivity.activity.caemeParticipants.any().user.id.in(activitySearchRequest.getCaemeUserIds());
		if (predicate != null) {
			finalPredicate = predicate.and(finalPredicate);
		}
		return finalPredicate;
	}

}
