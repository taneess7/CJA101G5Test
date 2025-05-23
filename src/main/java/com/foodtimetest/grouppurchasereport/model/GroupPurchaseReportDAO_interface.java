package com.foodtimetest.grouppurchasereport.model;

import java.util.*;

public interface GroupPurchaseReportDAO_interface {

	public void insert(GroupPurchaseReportVO GroupPurchaseReportVO);
	public void update(GroupPurchaseReportVO GroupPurchaseReportVO);
	public void delete(Integer reportId);
	
	public GroupPurchaseReportVO findByPrimaryKey(Integer reportId);
	public List<GroupPurchaseReportVO> getAll();
	// 查詢特定會員檢舉紀錄
	public List<GroupPurchaseReportVO> findByMemberId(Integer memId);
	// 查詢特定團購案的檢舉紀錄
	public List<GroupPurchaseReportVO> findByGBId(Integer gbId);
}
