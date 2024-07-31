package org.HotelRecommand.repository;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.AdminModel;

public class AdminRepository extends Dbconfig 
{
   public boolean CheckAdmin(AdminModel Amodel)
   {
	   try
	   {
		   stmt=conn.prepareStatement("select * from Admin where username=? and password=?");
		   stmt.setString(1, Amodel.getAusername());
		   stmt.setString(2, Amodel.getApassword());
		   rs=stmt.executeQuery();
		   if(rs.next())
		   {
			   return true;
			  
		   }
		   else
		   {
			   return false;
		   }
	   }catch(Exception ex)
	   {
		   System.out.println("Error is"+ex);
	   }
	return false;
}
}
