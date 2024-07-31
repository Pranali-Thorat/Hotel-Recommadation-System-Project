package org.HotelRecommand.service;

import java.util.List;

import org.HotelRecommand.model.HotelModel;
import org.HotelRecommand.repository.HotelRepository;

public class HotelService {
      HotelRepository hotelRepo=new HotelRepository();
      
	public boolean isAddHotelDetails(HotelModel hmodel) 
	{
	
		return hotelRepo.AddNewHotel(hmodel);
	}

	public List<HotelModel> getAllHotels(int aid) {
		
		return hotelRepo.getAllHotels(aid);
	}
	public boolean DeleteHotelByHotelId(int hid) {
		
		return hotelRepo.DeleteHotelByHotelId(hid);
	}
	
 public int getHotelIdByNameAndArea(String hname, int aid) {
		
		return hotelRepo.getHotelIdByNameAndArea(hname, aid);
	}

public List<HotelModel> getAllvegHotels(String areaname, String ftype) {
	
	return hotelRepo.getAllVegHotels(areaname, ftype);
}
}
