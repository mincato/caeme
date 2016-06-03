package com.caeme.activityreport.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caeme.activityreport.core.dto.ActivityReport;
import com.caeme.activityreport.core.model.Activity;
import com.caeme.activityreport.core.model.Area;
import com.caeme.activityreport.core.model.ParticipationTypeEnum;
import com.caeme.activityreport.core.service.ActivityService;
import com.caeme.activityreport.rest.dto.ActivityCreateRequest;
import com.caeme.activityreport.rest.dto.ActivitySearchRequest;
import com.caeme.activityreport.rest.exceptions.ValidationException;
import com.caeme.activityreport.rest.validators.ActivityRequestValidator;

@RestController
@RequestMapping(value = "/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityRequestValidator activityRequestValidator;

	@RequestMapping(method = RequestMethod.GET)
	public List<Activity> list() {
		return activityService.findAllActivities();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Activity> getActivity(@PathVariable Integer id) {
		Activity activity = activityService.findById(id);
		if (activity != null) {
			return ResponseEntity.ok(activity);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Activity> createActivity(@RequestBody ActivityCreateRequest activityCreateRequest) throws ValidationException {
		activityRequestValidator.validate(activityCreateRequest);
		Activity newActivity = this.activityService.saveActivity(activityCreateRequest);
		newActivity = this.activityService.findById(newActivity.getId());
		return ResponseEntity
				.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newActivity.getId()).toUri())
				.body(newActivity);
	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActivity(@PathVariable Integer id, @RequestBody Activity activity) {
		this.activityService.updateActivity(id, activity);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteActivity(@PathVariable Integer id) {
		activityService.deleteActivity(id);
	}

	@RequestMapping(value = "/participation_types", method = RequestMethod.GET)
	public List<String> listParticipationTypes() {
		return ParticipationTypeEnum.getParticipationTypes();
	}
	
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public List<Area> listAreas() {
		return activityService.findAllAreas();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<ActivityReport> search(ActivitySearchRequest activitySearchRequest) {
		activitySearchRequest.validate();
		ActivityReport activityReport = activityService.search(activitySearchRequest);
		if (activityReport != null ) {
			return ResponseEntity.ok(activityReport);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
