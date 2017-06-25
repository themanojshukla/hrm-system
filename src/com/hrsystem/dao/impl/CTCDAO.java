package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrsystem.dao.ICTCDAO;
import com.hrsystem.model.CTC;
import com.hrsystem.model.Candidate;
import com.hrsytem.init.DBConnection;

public class CTCDAO implements ICTCDAO {

	private CTC ctc;

	public CTCDAO(CTC ctc) {
		this.ctc = ctc;
	}

	@Override
	public double getTotalSalary(Candidate candidate) {
		String query = "SELECT basic,hra,da FROM salary where candidate_id = ?";
		double returnValue = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, candidate.getId());
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getDouble("basic")
						+ result.getDouble("hra") + result.getDouble("da");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(returnValue);

		return returnValue;
	}

	@Override
	public void setBasic(double basic) {
		ctc.setBasic(basic);

	}

	@Override
	public void setHRA(double hra) {
		ctc.setHra(hra);

	}

	@Override
	public void setDA(double da) {
		ctc.setDa(da);

	}

}