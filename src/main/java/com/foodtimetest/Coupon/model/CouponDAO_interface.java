package com.foodtimetest.Coupon.model;

import java.util.List;



public interface CouponDAO_interface {
	public void insert(CouponVO couponVO);
	public void update(CouponVO couponVO);
	public void delete(Integer couId);
	public CouponVO findByPrimaryKey(Integer couId);//單一查詢出貨歷史
	public List<CouponVO> getAll();
}
