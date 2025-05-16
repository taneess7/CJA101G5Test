package com.foodtimetest.smg.model;



import java.util.*;

public  interface SmgDAO_interface {
	public  void insert(SmgVO smgVO);    
    public void update(SmgVO smgVO);    
    public SmgVO findByPrimaryKey(Integer SMG_ID);
    public List<SmgVO> getAll();
}
