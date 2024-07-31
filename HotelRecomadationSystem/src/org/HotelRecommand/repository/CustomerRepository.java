package org.HotelRecommand.repository;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.CustomerModel;

public class CustomerRepository extends Dbconfig
{
   public boolean CustomerLogin(CustomerModel cm)
   {
	   try
	   {
		 stmt=conn.prepareStatement("select custname,mobNo from customer where custname=? and mobNo=?");  
		 stmt.setString(1, cm.getCustName());
		 stmt.setString(2, cm.getCustNo());
		 rs=stmt.executeQuery();
		 if(rs.next())
		 {
		  return true;
	     }
		 else
		 {
			 return false;
		 }
	   }
	   catch(Exception ex)
	   {
		   System.out.println("Error is"+ex);
	   }
	return false;
	   
   }

public boolean AddCustomer(CustomerModel custmodel) {
	 try
	 {
		 stmt=conn.prepareStatement("insert into customer values('0',?,?,?,?,?,?)");
		 stmt.setString(1, custmodel.getCustName());
		 stmt.setString(2, custmodel.getCustNo());
		 stmt.setString(3, custmodel.getCustEmail());
		 stmt.setString(4, custmodel.getFtype());
		 stmt.setInt(5, custmodel.getCityid());
		 stmt.setInt(6, custmodel.getAreaid());
		 int value=stmt.executeUpdate();
		 return value>0?true:false;
      }
	 catch(Exception ex)
	 {
		 System.out.println("error is"+ex);
	 }
	return false;
   }

public int custIbByName(String cuser) {
	try
	{
		
		stmt=conn.prepareStatement("select custid from customer where custname=?");
		stmt.setString(1, cuser);
		rs=stmt.executeQuery();
		if(rs.next())
		{
			return 1;
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

public String getCityNameByCustId(int cstid) {
   try
   {
	   stmt=conn.prepareStatement("select c.cityname from customer ct inner join city c on c.cityid=ct.cityid where ct.custid=?");
	   stmt.setInt(1, cstid);
	   rs=stmt.executeQuery();
	   if(rs.next())
	   {
		   return rs.getString(1);
	   }
	   else
	   {
		   return null;
	   }
   }catch(Exception ex)
   {
	   System.out.println("Error is"+ex);
   }

	return null;
}

public String getAreaNameByCustId(int cstid) {
	   try
	   {
		   stmt=conn.prepareStatement("select a.areaname from customer ct inner join area a on a.areaid=ct.areaid where ct.custid=?");
		   stmt.setInt(1, cstid);
		   rs=stmt.executeQuery();
		   if(rs.next())
		   {
			   return rs.getString(1);
		   }
		   else
		   {
			   return null;
		   }
	   }catch(Exception ex)
	   {
		   System.out.println("Error is"+ex);
	   }

		return null;
	}
}
