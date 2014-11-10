package com.app.wms.engine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO<T> {
	
private Connection connection;
	
	/* Method to get a connection from database */
	protected final Connection getConnection() throws SQLException {
		/*
		if (connection != null)
			return connection;

		SQLServerDataSource dataSource;
		Connection _connection;
		String serverName = "db01.aprdev.com";
		Short portNumber = 1521;
		String serviceName = "aprdev";
		String userName = "scott"; 
		String password = "password";
		
		dataSource = new OracleDataSource();
		dataSource.setURL("jdbc:oracle:thin:@" + serverName + ":" + portNumber.toString() + ":" + serviceName);
		_connection = dataSource.getConnection(userName, password);
		_connection.setAutoCommit(false);
		
		this.connection = _connection;
		
		return _connection;
		}
		*/
		
		Connection connect = null;
		try{
                    //  XXX : FYA | Database Connection Setting
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/inventory","sa","qts1n@r");
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
	
	public final void commitTransaction() throws SQLException {
		connection.commit();
		
		connection.close();
	}
	
	public final void rollbackTransaction() throws SQLException  {
		connection.rollback();
		
		connection.close();
	}
	
	public abstract java.util.List<T> getAllData() throws SQLException;
	
	public abstract T getDataById(Object o) throws SQLException;
	
	public abstract void insert(T o) throws SQLException;
	
	public abstract void update(T o) throws SQLException;
	
	public abstract void delete(T o) throws SQLException;

}
