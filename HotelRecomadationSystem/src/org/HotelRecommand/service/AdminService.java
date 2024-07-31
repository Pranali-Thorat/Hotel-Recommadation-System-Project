package org.HotelRecommand.service;

import org.HotelRecommand.model.AdminModel;
import org.HotelRecommand.repository.AdminRepository;

public class AdminService 
{
	 AdminRepository aRepo=new AdminRepository();

	public boolean CheckAdmin(AdminModel Amodel) 
	{
		
		return aRepo.CheckAdmin(Amodel);
	}
}
  
