package com.caeme.activityreport.rest.util;

import java.io.IOException;

import com.caeme.activityreport.core.model.ParticipationTypeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomParticipationTypeDeserilizer extends JsonDeserializer<ParticipationTypeEnum> {

	@Override
	public ParticipationTypeEnum deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {
		ParticipationTypeEnum participationType = ParticipationTypeEnum.getValue(jp.getText());
		return participationType;
	}

}
