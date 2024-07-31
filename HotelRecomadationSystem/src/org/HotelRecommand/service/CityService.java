package org.HotelRecommand.service;

import java.util.List;

import org.HotelRecommand.model.CityModel;
import org.HotelRecommand.repository.CityRepository;

public class CityService {
   CityRepository cityRepo=new CityRepository();

public boolean AddNewCity(CityModel cmodel) {
	// TODO Auto-generated method stub
	return cityRepo.AddNewCity(cmodel);
}

public List<CityModel> getAllCities() {
	return cityRepo.getAllCities();
}

public int getCityIdByName(String cityname) {
	
	return cityRepo.getCityIdByName(cityname);
}

public boolean UpdateCity(CityModel model) {
	
	return cityRepo.UpdateCity(model);
}

public boolean DeleteCity(CityModel model) {
	// TODO Auto-generated method stub
	return cityRepo.DeleteCity(model);
}





   
}
