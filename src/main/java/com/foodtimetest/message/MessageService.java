package com.foodtimetest.message;
import java.util.List;

public class MessageService {
	
	private MessageDAO_interface dao;
		
	public MessageService() {
			dao = new MessageDAO();
		}
	public MessageVO addMessage(Integer postId, Integer memId, java.sql.Timestamp mesDate, String mesContent) {
		MessageVO messageVO = new MessageVO();
		messageVO.setPostId(postId);
		messageVO.setMemId(memId);
		messageVO.setMesDate(mesDate);
		messageVO.setMesContent(mesContent);
		dao.insert(messageVO);
		return messageVO;
	}
	
//	public MessageVO updateMessage(Integer mesId) {
//		MessageVO messageVO = new MessageVO();
//		messageVO.setMemId(mesId);
//		messageVO.setMesContent(null);
//	}

}
