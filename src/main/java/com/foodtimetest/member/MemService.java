package com.foodtimetest.member;
import java.sql.Date;
import java.util.List;

public class MemService {
	private MemDAO_interface dao;
	public MemService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMem(String memEmail,String memAccount,String memPassword,String memNickname,String memName,String memPhone,Byte memGender,String memCity,String memCityarea,String memAddress,byte[] memAvatar) {
		MemberVO memVO = new MemberVO();
		
		memVO.setMemEmail(memEmail);
		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemNickname(memNickname);
		memVO.setMemName(memName);
		memVO.setMemPhone(memPhone);
		memVO.setMemGender(memGender);
		memVO.setMemCity(memCity);
		memVO.setMemCityarea(memCityarea);
		memVO.setMemAddress(memAddress);
//		memVO.setMemCode(memCode);
		memVO.setMemAvatar(memAvatar);
//		memVO.setMemTime(memTime);
//		memVO.setTotalStarNum(totalStarNum);
//		memVO.setTotalReviews(totalReviews);
		dao.insert(memVO);
		
	
		return memVO;
	}
	public MemberVO updateMember(String memEmail,String memPassword,String memNickname,String memName,String memPhone,Byte memGender,String memCity,String memCityarea,String memAddress,byte[] memAvatar,Date memTime,Integer totalStarNum,Integer totalReviews) {
		MemberVO memVO = new MemberVO();
		
		memVO.setMemEmail(memEmail);
		memVO.setMemPassword(memPassword);
		memVO.setMemNickname(memNickname);
		memVO.setMemName(memName);
		memVO.setMemPhone(memPhone);
		memVO.setMemGender(memGender);
		memVO.setMemCity(memCity);
		memVO.setMemCityarea(memCityarea);
		memVO.setMemAddress(memAddress);
		memVO.setMemAvatar(memAvatar);
//		memVO.setMemTime(memTime);
//		memVO.setTotalStarNum(totalStarNum);
//		memVO.setTotalReviews(totalReviews);
		dao.update(memVO);
		
		
		return memVO;
	}
	public MemberVO updateMemberPermission(Byte memStatus,Byte memNoSpeak,Byte memNoGroup,Byte memNoJoinGroup) {
		MemberVO memVO = new MemberVO();
		
		memVO.setMemStatus(memStatus);
		memVO.setMemNoSpeak(memNoSpeak);
		memVO.setMemNoPost(memNoJoinGroup);
		memVO.setMemNoGroup(memNoGroup);
		memVO.setMemNoJoingroup(memNoJoinGroup);
		dao.updatePermission(memVO);
		
		
		
		return memVO;
	}
	
	public void delete(Integer memId) {
		dao.delete(memId);
	}
	
	public MemberVO getOneMember(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}
	public boolean isAccountExists(String memAccount) {
        return dao.isAccountExist(memAccount);
	}
	public List<MemberVO> getAll(){
		return dao.getAll();
	}

}
