package com.caeme.activityreport.rest.dto;

import java.util.Date;
import java.util.List;

public class ActivityCreateRequest {

	private String title;

	private Integer area;

	private Integer createdBy;

	private Date startDate;

	private Date endDate;

	private String location;

	private String description;
	
	private List<CaemeParticipant> caemeParticipants;

	private List<Participant> otherParticipants;

	private String observation;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CaemeParticipant> getCaemeParticipants() {
		return caemeParticipants;
	}

	public void setCaemeParticipants(List<CaemeParticipant> caemeParticipants) {
		this.caemeParticipants = caemeParticipants;
	}

	public List<Participant> getOtherParticipants() {
		return otherParticipants;
	}

	public void setOtherParticipants(List<Participant> otherParticipants) {
		this.otherParticipants = otherParticipants;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}


}
