package com.caeme.activityreport.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caeme.activityreport.core.dto.ActivityReport;
import com.caeme.activityreport.core.model.Activity;
import com.caeme.activityreport.core.model.Area;
import com.caeme.activityreport.core.model.CaemeParticipant;
import com.caeme.activityreport.core.model.Participant;
import com.caeme.activityreport.core.model.QActivity;
import com.caeme.activityreport.core.model.User;
import com.caeme.activityreport.core.repositories.ActivityRepository;
import com.caeme.activityreport.core.repositories.AreaRepository;
import com.caeme.activityreport.rest.dto.ActivityCreateRequest;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private ActivityFiltering activityFiltering;
	
	@Override
	public Activity saveActivity(ActivityCreateRequest activityCreateRequest) {
		Activity activity = new Activity();
		activity.setArea(new Area(activityCreateRequest.getArea()));
		activity.setCreatedBy(new User(activityCreateRequest.getCreatedBy()));
		activity.setDescription(activityCreateRequest.getDescription());
		activity.setStartDate(activityCreateRequest.getStartDate());
		activity.setEndDate(activityCreateRequest.getEndDate());
		activity.setLocation(activityCreateRequest.getLocation());
		activity.setObservation(activityCreateRequest.getObservation());
		activity.setTitle(activityCreateRequest.getTitle());
		List<CaemeParticipant> caemeParticipants = activityCreateRequest.getCaemeParticipants().stream().map(caemeParticipant -> {
			CaemeParticipant modelParticipant = new CaemeParticipant();
			modelParticipant.setUser(new User(caemeParticipant.getUser()));
			modelParticipant.setParticipationType(caemeParticipant.getParticipationType());
			modelParticipant.setOrganizer(caemeParticipant.isOrganizer());
			modelParticipant.setObservation(caemeParticipant.getObservation());
			return modelParticipant;
		}).collect(Collectors.toList());
		activity.setCaemeParticipants(caemeParticipants);
		if (activityCreateRequest.getOtherParticipants() != null) {
			List<Participant> otherParticipants = activityCreateRequest.getOtherParticipants().stream().map(otherParticipant -> {
				Participant modelParticipant = new Participant();
				modelParticipant.setName(otherParticipant.getName());
				modelParticipant.setParticipationType(otherParticipant.getParticipationType());
				modelParticipant.setObservation(otherParticipant.getObservation());
				return modelParticipant;
			}).collect(Collectors.toList());
			activity.setOtherParticipants(otherParticipants);
		}
		Activity newActivity = activityRepository.save(activity);
		return newActivity;
	}

	@Override
	public List<Activity> findAllActivities() {
		return activityRepository.findAll();
	}

	@Override
	public void deleteActivity(Integer id) {
		Activity activity = activityRepository.findOne(id);
		if (activity != null) {
			activityRepository.delete(id);
		} else {
			//throw exception
		}
	}

	@Override
	public Activity findById(Integer id) {
		Activity activity = activityRepository.findOne(id);
		activity.getArea().getName();
		activity.getCaemeParticipants().size();
		if (activity.getOtherParticipants() != null) {
			activity.getOtherParticipants().size();
		}
		return activity;
	}

	@Override
	public void updateActivity(Integer id, Activity updateActivity) {
		Activity activity = activityRepository.findOne(id);
		if (activity != null) {
			activity.update(updateActivity);
			activityRepository.save(activity);
		}
	}

	@Override
	public List<Area> findAllAreas() {
		return areaRepository.findAll();
	}

	@Override
	public ActivityReport search(ActivitySearchRequest activitySearchRequest) {
		ActivityReport activityReport = new ActivityReport();
		BooleanExpression predicate = activityFiltering.filter(activitySearchRequest);
		Iterable<Activity> activitiesIterable = this.activityRepository.findAll(predicate, QActivity.activity.startDate.desc());
		List<Activity> activities = Lists.newArrayList(activitiesIterable);
		List<Activity> paginatedActivities = activities.subList(getBeginIndexOfPage(activitySearchRequest), getEndIndexOfPage(activitySearchRequest, activities));
		paginatedActivities.stream().forEach(activity -> activity.getOtherParticipants().size());
		activityReport.setActivities(paginatedActivities);
		activityReport.execute(activities);
		return activityReport;
	}
	
	public void saveActivity(Activity activity) {
		this.activityRepository.save(activity);
	}

	private int getBeginIndexOfPage(ActivitySearchRequest activitySearchRequest){
		return (activitySearchRequest.getPage() - 1) * activitySearchRequest.getPer_page();
	}

	private int getEndIndexOfPage(ActivitySearchRequest activitySearchRequest, List<Activity> activities){
		int index = activitySearchRequest.getPage() * activitySearchRequest.getPer_page();
		return index > activities.size() ? activities.size() : index;
	}

}
