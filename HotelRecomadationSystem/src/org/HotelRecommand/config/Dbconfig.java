package org.HotelRecommand.config;
import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Dbconfig {
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	protected CallableStatement cstmt;
	
	public Dbconfig()     //connected code in this
	{
		try
		{
			Class.forName(PathHelper.p.getProperty("db.driverClass"));
			conn=DriverManager.getConnection(PathHelper.p.getProperty("db.url"),
			PathHelper.p.getProperty("db.username"),
			PathHelper.p.getProperty("db.password"));
//			System.out.println("Database Connected...");
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
	}

	public static void main(String[] args) {
            new Dbconfig();
	}
}
