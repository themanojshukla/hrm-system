package com.hrsystem.dao;



public interface IAdminDAO {
	
	boolean authenticateAdmin();
	String getAdminName(String email);
	String getAdminName(int id);
	int getAdminID(String email);
	
	
}
