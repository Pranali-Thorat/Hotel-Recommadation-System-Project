package org.HotelRecommand.repository;

import org.HotelRecommand.config.Dbconfig;
import org.HotelRecommand.model.BookModel;

public class BookRepository extends Dbconfig
{
  public boolean BookHotel(BookModel bmodel,int hid,int custid)
  {
	  try
	  {
		stmt=conn.prepareStatement("insert into booktable values('0',?,?,?,?)");
	    stmt.setString(1, bmodel.getBooktime());
	    stmt.setString(2, bmodel.getBookdate());
	    stmt.setInt(3, hid);
	    stmt.setInt(4, custid);
	    int value=stmt.executeUpdate();
	    return value>0?true:false;
	    
	  }
	  catch(Exception ex)
	  {
		  System.out.println("error is"+ex);
	  }
	return false;
  }
}
