package com.caeme.activityreport.core.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.caeme.activityreport.core.model.Area;

public interface AreaRepository extends CrudRepository<Area, Integer>{
	
	List<Area> findAllByOrderByNameAsc();
	
}
