package com.caeme.activityreport.rest.validators;

import com.caeme.activityreport.rest.dto.ActivityCreateRequest;
import com.caeme.activityreport.rest.exceptions.ValidationException;

public interface ActivityRequestValidator {

	void validate(ActivityCreateRequest activityCreateRequest) throws ValidationException;

}
