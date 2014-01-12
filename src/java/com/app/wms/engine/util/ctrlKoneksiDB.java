package com.app.wms.engine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ctrlKoneksiDB {

	public ctrlKoneksiDB(){
		
	}
	
	public Connection openConnection(){
		Connection connect = null;
		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/inventory","sa","sa");
			return connect;
		}
		catch(SQLException se){
			System.out.println("Connection Failed! Error @ : " +se.getMessage());
			return null;
			
		}
		catch(Exception ex){
			System.out.println("Failed to Connect Database! Error @ : " +ex.getMessage());
			return null;
		}
		
	}
}
