package com.caeme.activityreport.rest.dto;

import com.caeme.activityreport.core.model.ParticipationType;

public class Participant {

	private String name;

	private ParticipationType participationType;

	private String observation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public ParticipationType getParticipationType() {
		return participationType;
	}

	public void setParticipationType(ParticipationType participationType) {
		this.participationType = participationType;
	}

}
