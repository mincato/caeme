package com.caeme.activityreport.rest.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {
		try {
			return DateUtils.parseDate(jp.getText(), "dd/MM/yyyy");
		} catch (ParseException e) {
			return null;
		}
	}

}
