package com.foodtimetest.groupbuyingcases.model;

import java.util.*;

public interface GroupBuyingCasesDAO_interface {
	
	public void insert(GroupBuyingCasesVO groupBuyingCasesVO);
	public void update(GroupBuyingCasesVO groupBuyingCasesVO);
	public void delete(Integer gbId);
	
	public GroupBuyingCasesVO findByPrimaryKey(Integer gbId);
	public List<GroupBuyingCasesVO> getAll();
	//查詢某會員開的所有團購案（MEM_ID）
	public List<GroupBuyingCasesVO> findByMemId(Integer memId);
	// 查詢某店家開的所有團購案（STOR_ID）
	public List<GroupBuyingCasesVO> findByStorId(Integer storId);
	// 查詢某商品對應的團購案（GB_PROD_ID）
	public List<GroupBuyingCasesVO> findByGBProdId(Integer gbProdId);

}





