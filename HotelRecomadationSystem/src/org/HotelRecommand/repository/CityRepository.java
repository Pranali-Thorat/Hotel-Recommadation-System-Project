package org.HotelRecommand.repository;

import java.util.*;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.CityModel;
import org.HotelRecommand.model.CityModel;

public class CityRepository  extends Dbconfig
{  
	List<CityModel> list;
    public boolean AddNewCity(CityModel cmodel)
    {
    	
    	try
    	{
    		stmt=conn.prepareStatement("insert into city values('0',?)");
    		stmt.setString(1, cmodel.getCityname());
    		 int value=stmt.executeUpdate();
  		     return value>0?true:false;
    		
    	}catch(Exception ex)
    	{
    		System.out.println("Error is"+ex);
    		return false;
    	}
	}
    public List<CityModel> getAllCities()
    {
    	try
    	{
    	 list=new ArrayList<CityModel>();
    	stmt=conn.prepareStatement("Select * from city");
    	 rs=stmt.executeQuery();
		  while(rs.next())
		  {
			  CityModel model=new CityModel();
			  model.setCityid(rs.getInt(1));
			  model.setCityname(rs.getString(2));
			  list.add(model);
		  }
		  return list.size()>0?list:null;
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Error is"+ex);
    	}
    	
		return null;
   }
    public int getCityIdByName(String cityname)
    {
 	   try
 	   {
 		   stmt=conn.prepareStatement("select cityid from city where cityname=?");
 		   stmt.setString(1, cityname);
 		   rs=stmt.executeQuery();
 		   if(rs.next())
 		   {
 			   return rs.getInt(1);
 		   }
 		   else
 		   {
 			   return 0;
 		   }
 	   }catch(Exception ex)
 	   {
 		   return -1;
 	   }
    }
    public boolean UpdateCity(CityModel cmodel)
    {
    	
    	try
    	{  
    		
    		stmt=conn.prepareStatement("update city set cityname=? where cityid=?");
    		stmt.setString(1, cmodel.getCityname());
    		stmt.setInt(2, cmodel.getCityid());
    		 int value=stmt.executeUpdate();
  		     return value>0?true:false;
    		
    	}catch(Exception ex)
    	{
    		System.out.println("Error is"+ex);
    		return false;
    	}
	}
	public boolean DeleteCity(CityModel model)
	{
	  try
	  {
		 
		  stmt=conn.prepareStatement("delete from city where cityid=?");
		  stmt.setInt(1, model.getCityid());
		  int value=stmt.executeUpdate();
		  return value>0?true:false;
		  
	  }catch(Exception ex)
	  {
		  System.out.println("Error is"+ex);
	  }
		return false;
	}
}
