package com.caeme.activityreport.rest.validators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.caeme.activityreport.rest.dto.ActivityCreateRequest;
import com.caeme.activityreport.rest.dto.ValidationError;
import com.caeme.activityreport.rest.exceptions.ValidationException;

@Component
public class ActivityRequestValidatorImpl implements ActivityRequestValidator {

	@Override
	public void validate(ActivityCreateRequest activityRequest) throws ValidationException {
		List<ValidationError> validationErrors = new ArrayList<>();
		if (StringUtils.isBlank(activityRequest.getTitle())) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1001");
			validationError.setField("title");
			validationError.setMessage("El titulo es requerido");
			validationErrors.add(validationError);
		}
		if (StringUtils.isBlank(activityRequest.getLocation())) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1002");
			validationError.setField("location");
			validationError.setMessage("La ubicacion es requerida");
			validationErrors.add(validationError);
		}
		if (activityRequest.getArea() == null) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1003");
			validationError.setField("area");
			validationError.setMessage("El area es requerida");
			validationErrors.add(validationError);
		}
		if (activityRequest.getCreatedBy() == null) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1004");
			validationError.setField("createdBy");
			validationError.setMessage("El creador es requerido");
			validationErrors.add(validationError);
		}
		if (activityRequest.getStartDate() == null) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1005");
			validationError.setField("startDate");
			validationError.setMessage("La fecha inicial es requerida");
			validationErrors.add(validationError);
		}
		if (activityRequest.getEndDate() == null) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1006");
			validationError.setField("endDate");
			validationError.setMessage("La fecha final es requerida");
			validationErrors.add(validationError);
		}
		if (activityRequest.getStartDate() != null && activityRequest.getEndDate() != null
				&& activityRequest.getStartDate().after(activityRequest.getEndDate())) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1301");
			validationError.setField("endDate");
			validationError.setMessage("La fecha final debe ser mayor o igual a la fecha inicial");
			validationErrors.add(validationError);
		}
		if (activityRequest.getCaemeParticipants() == null || activityRequest.getCaemeParticipants().isEmpty()) {
			ValidationError validationError = new ValidationError();
			validationError.setCode("1007");
			validationError.setField("caemeParticipants");
			validationError.setMessage("Debe haber al menos un participante caeme");
			validationErrors.add(validationError);
		} else {
			activityRequest.getCaemeParticipants().stream().forEach(caemeParticipant -> {
				if (caemeParticipant.getUser() == null) {
					ValidationError validationError = new ValidationError();
					validationError.setCode("1101");
					validationError.setField("caemeParticipant.user");
					validationError.setMessage("El usuario para el participante caeme es requerido");
					validationErrors.add(validationError);
				}
				if (caemeParticipant.getParticipationType() == null) {
					ValidationError validationError = new ValidationError();
					validationError.setCode("1102");
					validationError.setField("caemeParticipant.participationType");
					validationError.setMessage("El tipo de participacion para el participante caeme es requerido");
					validationErrors.add(validationError);
				}
			});
			if (activityRequest.getCaemeParticipants().stream().filter(participant -> participant.isOrganizer())
					.count() != 1) {
				ValidationError validationError = new ValidationError();
				validationError.setCode("1103");
				validationError.setField("caemeParticipant.organizer");
				validationError.setMessage("Debe haber un solo organizador");
				validationErrors.add(validationError);
			}
		}

		if (activityRequest.getOtherParticipants() != null) {
			activityRequest.getOtherParticipants().stream().forEach(otherParticipant -> {
				if (StringUtils.isBlank(otherParticipant.getName())) {
					ValidationError validationError = new ValidationError();
					validationError.setCode("1201");
					validationError.setField("otherParticipant.user");
					validationError.setMessage("El usuario para otro participante es requerido");
					validationErrors.add(validationError);
				}
				if (otherParticipant.getParticipationType() == null) {
					ValidationError validationError = new ValidationError();
					validationError.setCode("1202");
					validationError.setField("otherParticipant.participationType");
					validationError.setMessage("El tipo de participacion para otro participante es requerido");
					validationErrors.add(validationError);
				}
			});
		}

		if (!validationErrors.isEmpty()) {
			throw new ValidationException(validationErrors);
		}

	}

}
