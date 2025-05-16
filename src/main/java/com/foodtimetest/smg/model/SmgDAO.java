package com.foodtimetest.smg.model;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SmgDAO implements SmgDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(SmgVO smgVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SmgVO smgVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SmgVO findByPrimaryKey(Integer SMG_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmgVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
