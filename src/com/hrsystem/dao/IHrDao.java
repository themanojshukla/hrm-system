package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.HR;

public interface IHrDao {

	void removeExistingByEmail(String email);
	void removeExistingById(int id);

	boolean isExistingByEmail(String email);
	boolean isExistingById(int Id);

	String getNameByEmail(String email);
	String getNameById(int id);

	int getIdByEmail(String email);

	List<HR> getHRList();

	HR getHRByEmail(String email);
	HR getHRById(int id);

	void updateHr(HR hr);

	boolean isOtherExistingByEmail(String hrEmail, int id);

	boolean authenticateHR(String email, String password);

	void addNewHR(HR hr);

}