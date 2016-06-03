package com.caeme.activityreport.core.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	private Area area;

	@ManyToOne(fetch = FetchType.EAGER)
	private User createdBy;

	private Date startDate;

	private Date endDate;

	private String location;

	@Lob
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CaemeParticipant> caemeParticipants;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Participant> otherParticipants;

	@Lob
	private String observation;

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
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

	public void update(Activity updateActivity) {
		this.title = updateActivity.getTitle();
		this.area = updateActivity.getArea();
		this.createdBy = updateActivity.getCreatedBy();
		this.startDate = updateActivity.getStartDate();
		this.endDate = updateActivity.getEndDate();
		this.location = updateActivity.getLocation();
		this.description = updateActivity.getDescription();
		this.caemeParticipants = updateActivity.getCaemeParticipants();
		this.otherParticipants = updateActivity.getOtherParticipants();
		this.observation = updateActivity.getObservation();
	}

}
