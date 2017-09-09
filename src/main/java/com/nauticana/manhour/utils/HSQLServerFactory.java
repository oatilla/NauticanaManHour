package com.nauticana.manhour.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import org.hsqldb.server.Server;

public class HSQLServerFactory {
	
	public static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
	
	private static final Hashtable<String, Server> servers = new Hashtable<String, Server>();
	private static final String DATAROOT = "D:/WORK/GAMA/DATA/";

	public static synchronized void startdb(String name, int port) {
		Server  server = servers.get(name);
		if (server == null) {
            server = new Server();  
            server.setAddress("localhost");  
            server.setPort(port);  
            server.setDatabasePath(0, DATAROOT + name);  
            server.setDatabaseName(0, name);  
            server.start();
			servers.put(name, server);
		}
	}

	public void stopdb(String name) {
		Server  server = servers.get(name);
		if (server != null)
			server.stop();
	}
	
    public static boolean started(String name) {
		Server  server = servers.get(name);
    	if (server == null) return false;
    	return (server.getState() == 1);
    }
    
	public static String url(String name) {
		Server  server = servers.get(name);
		
		if (server == null)
			return null;
		else
			return "jdbc:hsqldb:hsql://" + server.getAddress() + ":" + server.getPort() + "/" + server.getDatabaseName(0, false);
	}
	
	public static Connection getConnection(String dbname, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(url(dbname), username, password);
	}
}