package com.foodtimetest.memfavlist;

import java.util.List;

public interface FavoriteListDAO_interface {
	public void insert(FavoriteListVO favoriteListVO);
	public void deleteByMemIdAndProdId(Integer memId, Integer prodId); // 刪單筆
    public void deleteAllByMemId(Integer memId); // 刪整個會員的收藏
	public FavoriteListVO findByPrimaryKey(Integer memId, Integer prodId);
	public List<FavoriteListVO> getAll();
}
