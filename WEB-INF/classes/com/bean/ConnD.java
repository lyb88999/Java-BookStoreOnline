package com.bean;
import java.sql.*;
public class ConnD {
	private String dri;
	private String url;
	private String username;
	private String passname;
	private Connection conn=null;
	
	public  ConnD()
	{
		try
		{
			dri="com.mysql.cj.jdbc.Driver";
			Class.forName(dri);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		public Connection getConnection()
		{
			try
			{
				url="jdbc:mysql://localhost:3306/webwork?serverTimezone=UTC";
				username="root";
				passname="lyb8889999";
				conn=DriverManager.getConnection(url,username,passname);
				if(conn!=null) {
					System.out.println("连接成功");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return conn;
		}
	  public ResultSet cx(String sql) {
		  try {
		  Statement stmt=conn.createStatement();
		  ResultSet res=stmt.executeQuery(sql);
		  return res;
		  }catch(SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
	  }
	  public boolean cr(String sql) {
		  try {
			  Statement stmt=conn.createStatement();
			  stmt.execute(sql);
			  return true;
		  }catch(SQLException e) {
			  e.printStackTrace();
			  return false;
		  }
	  }
	  public int xg(String sql) {
		  try {
			  Statement stmt=conn.createStatement();
			  int res=stmt.executeUpdate(sql);
			  return res;
		  }catch(SQLException e) {
			  e.printStackTrace();
			  return -1;
		  }
	  }
	  
	  public void close()
	  {
		  try 
		  {
			  conn.close();
		  }
		  catch(SQLException e)
		  {
			  e.printStackTrace();
		  }
	  }
			
	}