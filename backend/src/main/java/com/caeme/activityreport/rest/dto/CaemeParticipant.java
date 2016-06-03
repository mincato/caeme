package com.caeme.activityreport.rest.dto;

import com.caeme.activityreport.core.model.ParticipationType;

public class CaemeParticipant {

	private Integer user;

	private ParticipationType participationType;

	private String observation;

	private boolean organizer;

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public ParticipationType getParticipationType() {
		return participationType;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getObservation() {
		return observation;
	}

	public void setOrganizer(boolean organizer) {
		this.organizer = organizer;
	}

	public void setParticipationType(ParticipationType participationType) {
		this.participationType = participationType;
	}
	
	public boolean isOrganizer() {
		return organizer;
	}

}
