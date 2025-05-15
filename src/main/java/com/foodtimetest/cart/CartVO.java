package com.foodtimetest.cart;

import java.io.Serializable;

public class CartVO implements Serializable {
	//寫上所有欄位
    private Integer shopId;   // 購物車商品編號
    private Integer memId;    // 會員編號
    private Integer prodId;   // 商品編號

    //取得or設置_購物車商品編號
    public Integer getShopId() {
        return shopId;
    }
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    
    //取得or設置_會員編號
    public Integer getMemId() {
        return memId;
    }
    public void setMemId(Integer memId) {
        this.memId = memId;
    }
    
    //取得or設置商品編號
    public Integer getProdId() {
        return prodId;
    }
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
}
