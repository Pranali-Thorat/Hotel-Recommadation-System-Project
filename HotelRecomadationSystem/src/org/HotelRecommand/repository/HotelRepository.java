package org.HotelRecommand.repository;

import java.util.*;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.HotelModel;

public class HotelRepository  extends Dbconfig
{
	ArrayList<HotelModel> list;
  public boolean AddNewHotel(HotelModel hmodel)
  {
	  try
	  {
		  stmt=conn.prepareStatement("insert into Hotel values('0',?,?,?,?,?)");
		  stmt.setString(1, hmodel.getHname());
          stmt.setString(2, hmodel.getHcon());
          stmt.setString(3, hmodel.getHadd());
          stmt.setString(4, hmodel.getHtype());
          stmt.setInt(5, hmodel.getAreaid());
          int value=stmt.executeUpdate();
          return value>0?true:false;
	  
	  }catch(Exception ex)
	  {
		  System.out.println("Error is"+ex);
	  }
	return false;  
	  
  }
  
 public ArrayList<HotelModel> getAllHotels(int areaid) {
	 try
	  {
		  list= new ArrayList<HotelModel>();
		  stmt=conn.prepareStatement("select * from hotel where areaid=? ");
		  stmt.setInt(1, areaid);
		  System.out.println(areaid);
		  rs=stmt.executeQuery();
		 
			while (rs.next()) {
				HotelModel model = new HotelModel();
				model.setHid(rs.getInt(1));
				model.setHname(rs.getString(2));
				model.setHcon(rs.getString(3));
				model.setHadd(rs.getString(4));
				model.setHtype(rs.getString(5));
				list.add(model);
			}
			return list.size() > 0 ? list:null;
		} 
	   catch(Exception ex)
	   {
		System.out.println("Error is"+ex);	
	   }
	  return null;
    }
 
 public boolean DeleteHotelByHotelId(int hid) {
   
	 try
	 {
		 stmt=conn.prepareStatement("delete from hotel where hid=?");
		 stmt.setInt(1, hid);
		 int value=stmt.executeUpdate();
		 return value>0?true:false;
	 }
	 catch(Exception ex)
	 {
		 System.out.println("Error is"+ex);
	 }
	return false;
}

public int getHotelIdByNameAndArea(String hotelname,int areaid) {
	try
	{
		stmt=conn.prepareStatement("select hid from hotel where hname=? and areaid=?");
		stmt.setString(1, hotelname);
		stmt.setInt(2, areaid);
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
public ArrayList<HotelModel>getAllVegHotels(String areaname,String type)
{
	try {
		list=new ArrayList<HotelModel>();
		stmt=conn.prepareStatement("select h.hid,h.hname,h.hcon,h.hadd from hotel h inner join area a on a.areaid=h.areaid where areaname=? and htype=?");
		stmt.setString(1, areaname);
		stmt.setString(2, type);
		rs=stmt.executeQuery();
	    while(rs.next())
	    {
	    	HotelModel model=new HotelModel();
	    	model.setHid(rs.getInt(1));
	    	model.setHname(rs.getString(2));
	    	model.setHcon(rs.getString(3));
	    	model.setHadd(rs.getString(4));
//	    	model.setHotelType(rs.getString(5));
	    	list.add(model);
	    }
	    return list.size()>0?list:null;
		
	}
	catch(Exception ex)
	{
		System.out.println("Error is "+ex);
	}
	return null;
}
public ArrayList<HotelModel>getAllVegNonVegHotels(String areaname)
{
	try {
		list=new ArrayList<HotelModel>();
		
		stmt=conn.prepareStatement("select h.hid,h.hname,h.hcon,h.hadd,h.htype from hotel h inner join area a on a.areaid=h.areaid where areaname=?");
		stmt.setString(1, areaname);
	    rs=stmt.executeQuery();
	    while(rs.next())
	    {
	    	HotelModel model=new HotelModel();
	    	model.setHid(rs.getInt(1));
	    	model.setHname(rs.getString(2));
	    	model.setHcon(rs.getString(3));
	    	model.setHadd(rs.getString(4));
	    	model.setHtype(rs.getString(5));
	    	list.add(model);
	    }
	    return list.size()>0?list:null;
	}catch(Exception ex)
	{
		System.out.println("Error is : "+ex);
		return null;
	}
}
 
   }

