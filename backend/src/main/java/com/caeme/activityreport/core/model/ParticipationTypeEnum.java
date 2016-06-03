package com.caeme.activityreport.core.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum ParticipationTypeEnum {

	ORGANIZADOR("Organizador"), ASISTENTE("Asistente"), AUSPICIANTE("Auspiciante"), DISERTANTE("Disertante"), OTRO(
			"Otro");

	private String participationType;

	static final Map<String, ParticipationTypeEnum> participationTypeMap = new HashMap<String, ParticipationTypeEnum>();
	static final List<String> participationTypes = new LinkedList<String>();

	static {
		for (ParticipationTypeEnum participation : ParticipationTypeEnum.values()) {
			participationTypeMap.put(participation.participationType, participation);
			participationTypes.add(participation.participationType);
		}
	}

	private ParticipationTypeEnum(String participationType) {
		this.participationType = participationType;
	}

	public String getParticipationType() {
		return participationType;
	}

	public static ParticipationTypeEnum getValue(String participationType) {
		return participationTypeMap.get(participationType);
	}
	
	public static List<String> getParticipationTypes() {
		return participationTypes;
	}

}
