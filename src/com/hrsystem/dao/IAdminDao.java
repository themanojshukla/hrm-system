package com.hrsystem.dao;



public interface IAdminDao {
	
	boolean authenticateAdmin(String email, String passwd);
	String getAdminName(String email);
	String getAdminName(int id);
	int getAdminID(String email);
	
	
	
}
