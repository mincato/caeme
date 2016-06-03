package com.caeme.activityreport.core.dto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.caeme.activityreport.core.comparators.MonthComparator;
import com.caeme.activityreport.core.comparators.ValueComparator;
import com.caeme.activityreport.core.model.Activity;
import com.caeme.activityreport.core.util.DateUtil;

public class ActivityReport {

	private List<Activity> activities;

	private Map<String, Long> activitiesPerArea;

	private Map<String, Long> activitiesPerParticipant;

	private Map<String, Long> activitiesPerMonth;

	private int totalActivities;

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Map<String, Long> getActivitiesPerArea() {
		return activitiesPerArea;
	}

	public void setActivitiesPerArea(Map<String, Long> activitiesPerArea) {
		this.activitiesPerArea = activitiesPerArea;
	}

	public Map<String, Long> getActivitiesPerParticipant() {
		return activitiesPerParticipant;
	}

	public void setActivitiesPerParticipant(Map<String, Long> activitiesPerParticipant) {
		this.activitiesPerParticipant = activitiesPerParticipant;
	}

	public void setTotalActivities(int totalActivities) {
		this.totalActivities = totalActivities;
	}

	public int getTotalActivities() {
		return totalActivities;
	}

	public void setActivitiesPerMonth(Map<String, Long> activitiesPerMonth) {
		this.activitiesPerMonth = activitiesPerMonth;
	}

	public Map<String, Long> getActivitiesPerMonth() {
		return activitiesPerMonth;
	}

	public void execute(List<Activity> activities) {
		Map<String, Long> activitiesPerParticipant = activities.stream()
				.filter(activity -> !activity.getCaemeParticipants().isEmpty())
				.collect(Collectors.groupingBy(
						activity -> activity.getCaemeParticipants().stream()
								.filter(participant -> participant.isOrganizer()).findFirst().get().getUser().getName(),
						HashMap::new, Collectors.counting()));

		this.activitiesPerParticipant = sort(activitiesPerParticipant,
				new ValueComparator<String, Long>(activitiesPerParticipant));

		Map<String, Long> activitiesPerArea = activities.stream().collect(
				Collectors.groupingBy(activity -> activity.getArea().getName(), HashMap::new, Collectors.counting()));
		
		this.activitiesPerArea = sort(activitiesPerArea, new ValueComparator<String, Long>(activitiesPerArea).reversed());

		Map<String, Long> activitiesPerMonth = activities.stream().filter(activity -> activity.getStartDate() != null).collect(Collectors.groupingBy(
				activity -> DateUtil.transformMonth(activity.getStartDate()), HashMap::new, Collectors.counting()));

		this.activitiesPerMonth = sort(activitiesPerMonth, new MonthComparator());

		this.totalActivities = activities.size();

	}

	private Map<String, Long> sort(Map<String, Long> unsortedMap, Comparator<String> comparator) {
		TreeMap<String, Long> sortedMap = new TreeMap<>(comparator);
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}

}
