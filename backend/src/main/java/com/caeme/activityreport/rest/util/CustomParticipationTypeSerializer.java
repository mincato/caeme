package com.caeme.activityreport.rest.util;

import java.io.IOException;

import com.caeme.activityreport.core.model.ParticipationTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomParticipationTypeSerializer extends JsonSerializer<ParticipationTypeEnum> {

	@Override
	public void serialize(ParticipationTypeEnum participationType, JsonGenerator jg, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jg.writeString(participationType.getParticipationType());
	}

}
