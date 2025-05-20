package com.foodtimetest.member;

import java.util.List;

public interface MemDAO_interface {
	public void insert(MemberVO memberVO);//新增會員資料
	public void update(MemberVO memberVO);//更新會員資料
	public void updatePermission(MemberVO memberVO);//更新會員權限
	public void delete(Integer memId);//刪除會員資料
	public MemberVO findByPrimaryKey(Integer memId);//尋找某一個會員資料
	public List<MemberVO> getAll();//將會員資料放在陣列
}
