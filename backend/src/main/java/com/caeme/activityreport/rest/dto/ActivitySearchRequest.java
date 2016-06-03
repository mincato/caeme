package com.caeme.activityreport.rest.dto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

public class ActivitySearchRequest extends PageableRequest{
	
	private Date startDate;
	
	private Date endDate;
	
	private List<Integer> areaIds;
	
	private List<Integer> caemeUserIds;
	
	private String other_participant;
	
	private String areas;
	
	private String caeme_users;
	
	private String title;
	
	public Date getEndDate() {
		return endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setEndDate(String endDate) throws ParseException {
		this.endDate = DateUtils.parseDate(endDate, "dd/MM/yyyy");
	}
	
	public void setStartDate(String startDate) throws ParseException {
		this.startDate = DateUtils.parseDate(startDate, "dd/MM/yyyy");
	}
	
	public void setAreas(String areas) {
		this.areas = areas;
	}
	
	public void setOther_participant(String other_participant) {
		this.other_participant = other_participant;
	}
	
	public String getOther_participant() {
		return other_participant;
	}
	
	public void setCaeme_users(String caeme_users) {
		this.caeme_users = caeme_users;
	}
	
	public List<Integer> getAreaIds() {
		return areaIds;
	}
	
	public List<Integer> getCaemeUserIds() {
		return caemeUserIds;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	protected void validateRequest() {
					
		if (areas != null) {
			String[] areasToken = areas.split(",");
			areaIds = new ArrayList<>();
			for (String area : areasToken) {
				areaIds.add(Integer.parseInt(area));
			}
		}
		
		if (caeme_users != null) {
			String[] userToken = caeme_users.split(",");
			caemeUserIds = new ArrayList<>();
			for (String user : userToken) {
				caemeUserIds.add(Integer.parseInt(user));
			}
		}
	}

}
