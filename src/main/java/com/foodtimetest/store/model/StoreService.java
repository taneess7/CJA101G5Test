package com.foodtimetest.store.model;

import java.sql.Timestamp;
import java.util.List;



public class StoreService {

	private StoreDAO_interface dao;

	public StoreService() {
		dao = new StoreDAO();
	}

	public List<StoreVO> getAll() {
		return dao.getAll();
	}

}
