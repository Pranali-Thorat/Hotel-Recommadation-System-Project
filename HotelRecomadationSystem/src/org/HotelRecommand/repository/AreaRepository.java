package org.HotelRecommand.repository;

import java.util.*;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.AreaModel;
import org.HotelRecommand.model.CityModel;

public class AreaRepository extends Dbconfig{
  ArrayList<AreaModel> list;
	public boolean AddNewArea(AreaModel amodel) {
		try
		{
			stmt=conn.prepareStatement("insert into area values('0',?,?)");
			stmt.setString(1, amodel.getAreaname());
			stmt.setInt(2, amodel.getCid());
			int value=stmt.executeUpdate();
			return value>0?true:false;
			
		}catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return false;
	}
	public List<AreaModel>getAllAreasNames(String cityname)
	  {
		  try
		  {
			  list=new ArrayList<AreaModel>();
			  stmt=conn.prepareStatement("select areaid,areaname from area a inner join city c on c.cityid=a.cityid where cityname=? order by cityname");
			  stmt.setString(1, cityname);
			  rs=stmt.executeQuery();
			  while(rs.next())
			  {
				  AreaModel model=new AreaModel();
				  model.setAreaid(rs.getInt(1));
				  model.setAreaname(rs.getString(2));
				  list.add(model);
			  }
			  return list.size()>0?list:null;
         }catch(Exception ex)
		  {
           System.out.println("Exception is:"+ex);
           return null;
		  }
	  }
	
	public int getAreaIdByName(String areaname )
	{
		try
		{
			stmt=conn.prepareStatement(" select areaid from area  where areaname=?");
			stmt.setString(1, areaname);
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
	public boolean UpdateAreaName(String oldarea,String newarea)
	{
		try
		{
			stmt=conn.prepareStatement(" update area set areaname=? where areaname=?");
			stmt.setString(1,newarea);
            stmt.setString(2,oldarea);
            int value=stmt.executeUpdate();
            return value>0?true:false;
			
		}catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return false;
	}
	public boolean DeleteArea(AreaModel amodel) {
		try
		{
			stmt=conn.prepareStatement(" delete from area where areaname =? and cityid=?");
			stmt.setString(1, amodel.getAreaname());
			stmt.setInt(2,amodel.getCid());
			int value=stmt.executeUpdate();
			return value>0?true:false;
			
		}catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return false;
	}
	public List<AreaModel> getAllAreas(int cityid) {
		try
		{
			list=new ArrayList<AreaModel>();
			stmt=conn.prepareStatement("select * from area where cityid=?");
			stmt.setInt(1, cityid);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				AreaModel amodel=new AreaModel();
				  amodel.setAreaid(rs.getInt(1));
				  amodel.setAreaname(rs.getString(2));
				  list.add(amodel);
			  }
			  return list.size()>0?list:null;
	    	}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return null;
	}
 }

