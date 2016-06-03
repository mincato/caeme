package com.caeme.activityreport.core.service;

import java.util.List;

import com.caeme.activityreport.core.model.Area;

public interface AreaService {
	
	int saveArea(Area area);
	 
    void deleteArea(Integer id);
 
    Area findById(Integer id);
 
    void updateArea(Integer id, Area activity);

	List<Area> findAllAreas();

}
