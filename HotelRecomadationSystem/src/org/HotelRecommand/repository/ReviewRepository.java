package org.HotelRecommand.repository;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.ReviewModel;

public class ReviewRepository extends Dbconfig
{

	public boolean AddReview(int hid, int custid,ReviewModel rmodel) {
		try
		{
			stmt=conn.prepareStatement("insert into reviewrating values('0',?,?,?,?)");
			stmt.setInt(1,hid);
			stmt.setInt(2, custid);
			stmt.setString(3, rmodel.getReview());
			stmt.setInt(4,rmodel.getRating());
			int value=stmt.executeUpdate();
			return value>0?true:false;
			
		}catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return false;
	}

}
