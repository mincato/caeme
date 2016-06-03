package com.caeme.activityreport.core.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ParticipationType {

	@Enumerated(EnumType.STRING)
	private ParticipationTypeEnum participationType;

	private String otherParticipationType;
	
	public ParticipationType() {
		this(null, null);
	}

	public ParticipationType(ParticipationTypeEnum participationTypeEnum, String otherParticipationType) {
		if (ParticipationTypeEnum.OTRO.equals(participationType)) {
			setOtherParticipationType(otherParticipationType);
		}
		setParticipationType(participationTypeEnum);
	}
	
	public ParticipationTypeEnum getParticipationType() {
		return participationType;
	}

	public String getOtherParticipationType() {
		return otherParticipationType;
	}
	
	private void setParticipationType(ParticipationTypeEnum participationType) {
		this.participationType = participationType;
	}

	private void setOtherParticipationType(String otherParticipationType) {
		this.otherParticipationType = otherParticipationType;
	}

}
