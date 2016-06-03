package com.caeme.activityreport.core.service;

import java.util.List;

import com.caeme.activityreport.core.dto.ActivityReport;
import com.caeme.activityreport.core.model.Activity;
import com.caeme.activityreport.core.model.Area;
import com.caeme.activityreport.rest.dto.ActivityCreateRequest;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;

public interface ActivityService {
	
	Activity saveActivity(ActivityCreateRequest activityCreateRequest);
	 
    List<Activity> findAllActivities();
 
    void deleteActivity(Integer id);
 
    Activity findById(Integer id);
 
    void updateActivity(Integer id, Activity activity);

	List<Area> findAllAreas();

	ActivityReport search(ActivitySearchRequest activitySearchRequest);

	void saveActivity(Activity activity);

}
