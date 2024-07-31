package org.HotelRecommand.service;

import java.util.List;

import org.HotelRecommand.model.AreaModel;
import org.HotelRecommand.repository.AreaRepository;

public class AreaService {
   AreaRepository areaRepo=new AreaRepository();
   
	public boolean AddNewArea(AreaModel amodel) {
	    return areaRepo.AddNewArea(amodel);
	}

	public List<AreaModel> getAllAreasNames(String cityname) {
		
		return areaRepo.getAllAreasNames(cityname);
	}

	public boolean UpdateAreaName(String oldarea, String newarea) {
		
		return areaRepo.UpdateAreaName(oldarea, newarea);
	}

	public boolean DeleteArea(AreaModel amodel) {
		// TODO Auto-generated method stub
		return areaRepo.DeleteArea(amodel);
	}

	public int getAreaIdByName(String areaname) {
		
		return  areaRepo.getAreaIdByName(areaname);
	}

	public List<AreaModel> getAllAreas(int cityid) {
		
		return areaRepo.getAllAreas(cityid);
	}

	
	
	}


