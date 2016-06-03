package com.caeme.activityreport.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caeme.activityreport.core.model.Area;
import com.caeme.activityreport.core.repositories.AreaRepository;

@Service
@Transactional
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public int saveArea(Area area) {
		Area newArea = areaRepository.save(area);
		return newArea.getId();
	}

	@Override
	public void deleteArea(Integer id) {
		Area area = areaRepository.findOne(id);
		if (area != null) {
			areaRepository.delete(id);
		} else {
			//throw exception
		}
	}

	@Override
	public Area findById(Integer id) {
		Area area = areaRepository.findOne(id);
		return area;
	}

	@Override
	public void updateArea(Integer id, Area updateArea) {
//		Area area = areaRepository.findOne(id);
//		if (area != null) {
//			area.update(updateArea);
//			areaRepository.save(area);
//		}
	}

	@Override
	public List<Area> findAllAreas() {
		return areaRepository.findAll();
	}
	
}
