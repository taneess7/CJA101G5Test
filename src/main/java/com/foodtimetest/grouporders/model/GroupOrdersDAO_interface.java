package com.foodtimetest.grouporders.model;

import java.util.*;

public interface GroupOrdersDAO_interface {

	public void insert(GroupOrdersVO GroupOrdersVO);
    public void update(GroupOrdersVO GroupOrdersVO);
    public void delete(Integer gbOrId);
	
    public GroupOrdersVO findByPrimaryKey(Integer gbOrId);
    public List<GroupOrdersVO> getAll();
    //查詢某一團購案的所有訂單
    public List<GroupOrdersVO> findByGBId(Integer gbId);
	//查詢某一會員的所有團購訂單
    public List<GroupOrdersVO> findByMemberId(Integer memId);
    
    
}



