package com.caeme.activityreport.core.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CaemeParticipant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	private ParticipationType participationType;

	private String observation;
	
	private boolean organizer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ParticipationType getParticipationType() {
		return participationType;
	}

	public void setParticipationType(ParticipationType participationType) {
		this.participationType = participationType;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	public boolean isOrganizer() {
		return organizer;
	}
	
	public void setOrganizer(boolean organizer) {
		this.organizer = organizer;
	}

}
