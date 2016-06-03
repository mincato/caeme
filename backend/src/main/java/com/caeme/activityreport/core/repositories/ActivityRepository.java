package com.caeme.activityreport.core.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.caeme.activityreport.core.model.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Integer>, QueryDslPredicateExecutor<Activity>{

	List<Activity> findAll();

	@Query(value = "select * from (select row_number() over (order by startDate desc) AS RowNum, * from Activity where startDate between ?1 and ?2) AS RowConstrainedResult WHERE RowNum >= ?3 and RowNum < ?4", nativeQuery = true)
	List<Activity> findByStartDateBetween(Date startDate, Date endDate, Integer start, Integer end);

}
