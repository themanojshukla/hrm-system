package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.HR;

public interface IHRDAO {

	void addNewHR();

	void removeExistingByEmail(String email);
	void removeExistingById(int id);

	boolean isExisting();
	boolean isExistingByEmail(String email);
	boolean isExistingById(int Id);

	String getNameByEmail(String email);
	String getNameById(int id);
	String getNameById();

	int getIdByEmail(String email);

	List<HR> getHRList();

	HR getHRByEmail(String email);
	HR getHRById(int id);

	void updateHr();

	boolean isOtherExistingByEmail(String hrEmail, int id);

}