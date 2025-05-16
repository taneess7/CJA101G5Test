package com.foodtimetest.product;

import java.io.Serializable;

public class ProductCategoryVO implements Serializable{

	private Integer prodCateId;
	private Integer prodCate;
	public Integer getProdCateId() {
		return prodCateId;
	}
	public void setProdCateId(Integer prodCateId) {
		this.prodCateId = prodCateId;
	}
	public Integer getProdCate() {
		return prodCate;
	}
	public void setProdCate(Integer prodCate) {
		this.prodCate = prodCate;
	}

}
