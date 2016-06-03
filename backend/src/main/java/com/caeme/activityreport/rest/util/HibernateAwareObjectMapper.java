package com.caeme.activityreport.rest.util;

import java.util.Date;

import com.caeme.activityreport.core.model.ParticipationTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class HibernateAwareObjectMapper extends ObjectMapper{
	
	private static final long serialVersionUID = 8001464118063583679L;

	public HibernateAwareObjectMapper() {
        Hibernate5Module hm = new Hibernate5Module();
        registerModule(hm);
        
        SimpleModule sm = new SimpleModule();
        sm.addDeserializer(Date.class, new CustomDateDeserializer());
        sm.addDeserializer(ParticipationTypeEnum.class, new CustomParticipationTypeDeserilizer());
        sm.addSerializer(Date.class, new CustomDateSerializer());
        sm.addSerializer(ParticipationTypeEnum.class, new CustomParticipationTypeSerializer());
        registerModule(sm);
	}

}
